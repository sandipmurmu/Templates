package com.api.rest.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovingAverage {

	@JsonProperty(value="Ticker")
	private String ticker;
	
	@JsonProperty(value="Avg")
	private Double price;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
