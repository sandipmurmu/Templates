package com.api.rest.util;

import java.time.LocalDate;

import javax.ws.rs.client.WebTarget;

public class TableRequest implements QuandlRequest{

	private static final String DATATABLE_PATH="datatables";
	private static final String DATABASE_CODE ="WIKI";
	private static final String DATA_CODE="PRICES";
	private static final String JSON_FORMAT=".json";
	
	
    /** Quandl table parameters
     */
	public static final String START_DATE = "date.gte";
	public static final String END_DATE = "date.lte";
	public static final String COL_NAMES="qopts.columns";
	public static final String TICKER="ticker";
	
	private static TableRequest _instance = null;

	
	private String _startDate;
	private String _endDate;
	private String _columns;
	private String _ticker;
	
	
	private TableRequest(String ticker) {
		_ticker = ticker;
	}
	
	public static TableRequest forTicker(String ticker) {
		_instance = new TableRequest(ticker);
		return _instance;
	}
	
	public TableRequest withStartDate(String date) {
		_startDate = date;
		return _instance;
	}
	
	public TableRequest withEndDate(String date) {
		_endDate = date;
		return _instance;
	}
	
		
	
    @Override
	public WebTarget appendPathAndQueryParameter(WebTarget webTarget) {
		
		WebTarget target = webTarget;
		target = target.path(DATATABLE_PATH);
		target = target.path(DATABASE_CODE);
		target = target.path(DATA_CODE + JSON_FORMAT);
		
		if(null!=_ticker) {
			target = target.queryParam(TICKER, _ticker);
		}
		if(null!=_columns) {
			target = target.queryParam(COL_NAMES, _columns);
		}
		if(null!=_ticker) {
			target = target.queryParam(START_DATE, _startDate);
		}
		if(null!=_endDate) {
			target = target.queryParam(END_DATE, _endDate);
		}
		return target;
	}
	
}
