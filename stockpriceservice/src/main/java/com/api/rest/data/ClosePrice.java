package com.api.rest.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class ClosePrice {

	@JsonProperty(value="Ticker")
	private String ticker;
	
	@JsonProperty(value="DateClose")
	private JsonNode data;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}

	

	
	
	
}
