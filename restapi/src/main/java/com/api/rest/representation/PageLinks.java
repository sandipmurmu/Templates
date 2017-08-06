package com.api.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageLinks<T> {

	@JsonProperty(value="result")
	private T result;
	
	@JsonProperty(value="pages")
	private Integer pages;

	public PageLinks() {
		
	}
	
	public PageLinks(T result, Integer pages) {
		this.result = result;
		this.pages = pages;
	}
	
	public T getResult() {
		return result;
	}

	public Integer getPages() {
		return pages;
	}
	
	
	
}
