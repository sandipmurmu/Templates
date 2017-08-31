package com.api.rest.quandl;

import com.api.rest.data.MovingAverage;
import com.api.rest.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;

public class MovingAverageTask{
	
	private QuandlRequest _quandleReq;
	private String	_authToken;
	private String _ticker;
	private Long _days;
	
	public MovingAverageTask(QuandlRequest quandlRequest,
			String authToken,
			String ticker,
			Long days) {
		_quandleReq = quandlRequest;
		_authToken = authToken;
		_ticker = ticker;
		_days = days;
	}
	
	
	public  MovingAverage execute() {
			QuandlDataService quandl = QuandlDataService.createSession(_authToken);
			String json = quandl.getData(_quandleReq);
			JsonNode data = JsonUtil.getData(json, "data").get(0).get(1);
			Double cumulativePrice = data.asDouble();
			Double average = calculateMovingAverage(cumulativePrice, _days);
			MovingAverage ma = new MovingAverage();
			ma.setPrice(average);
			ma.setTicker(_ticker);
		return ma;
	}
	
	private double calculateMovingAverage(
			Double cumulativePrice,
			Long days) {
		String s=days+"";
		Double d = Double.parseDouble(s);
		return (cumulativePrice/d);
	}
}
