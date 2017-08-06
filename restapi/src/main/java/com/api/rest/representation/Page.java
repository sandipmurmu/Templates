package com.api.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page<T> {

	@JsonProperty(value="page")
	private T result;
	
	public Page() {
		
	}
	
	public Page(T result) {
		this.result = result;
	}

	public T getResult() {
		return result;
	}
}
