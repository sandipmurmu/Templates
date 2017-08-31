package com.api.rest.quandl;

import javax.ws.rs.client.WebTarget;

public class TimeSeriesRequest implements QuandlRequest {

	private static final String DATATABLE_PATH="datasets";
	private static final String DATABASE_CODE ="WIKI";
	private static final String JSON_FORMAT=".json";
	
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	public static final String COL_INDEX="column_index";
	public static final String TRANSFORM="transform";
	public static final String LIMIT="limit";
	

	
	private static TimeSeriesRequest _instance = null;

	
	private String _startDate;
	private String _endDate;
	private String _columnIndex;
	private String _transform;
	private String _limit;
	/*This is the ticker*/
	private String _datacode;
	
	private TimeSeriesRequest(String datacode) {
		_datacode = datacode;
	}
	
	public static TimeSeriesRequest forTicker(String datacode) {
		_instance = new TimeSeriesRequest(DATABASE_CODE+"/"+datacode);
		return _instance;
	}
	
	
	
	public TimeSeriesRequest withStartDate(String date) {
		_startDate = date;
		return _instance;
	}
	
	public TimeSeriesRequest withEndDate(String date) {
		_endDate = date;
		return _instance;
	}
	
	public TimeSeriesRequest withColumnIndex(String columnIndex) {
		_columnIndex = columnIndex;
		return _instance;
	}
	
	public TimeSeriesRequest withTransform(String transform) {
		_transform = transform;
		return _instance;
	}
	
	public TimeSeriesRequest withLimit(String limit) {
		_limit = limit;
		return _instance;
	}
	
	
	@Override
	public WebTarget appendPathAndQueryParameter(WebTarget webTarget) {
		WebTarget target = webTarget;
		target = target.path(DATATABLE_PATH);
		target = target.path(_datacode+JSON_FORMAT);
		
		
		if(null!=_startDate) {
			target = target.queryParam(START_DATE, _startDate);
		}
		
		if(null!=_endDate) {
			target = target.queryParam(END_DATE, _endDate);
		}
		if(null!=_columnIndex) {
			target = target.queryParam(COL_INDEX, _columnIndex);
		}
		if(null!=_transform) {
			target = target.queryParam(TRANSFORM, _transform);
		}
		if(null!=_limit) {
			target = target.queryParam(LIMIT, _limit);
		}
		
		return target;
	}

}
