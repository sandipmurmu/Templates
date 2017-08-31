package com.api.rest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class QuandlSession {

	
	
	private String _authToken;
	
	public String getJson(final WebTarget target) throws IOException{
		Builder requestBuilder = target.request();
	    Response response = requestBuilder.buildGet().invoke();
	    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	    	InputStream inputStream = response.readEntity(InputStream.class);
	    	try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
	    		return reader.lines().collect(Collectors.joining("\n"));
	    	} 
	     }else {
	    	String msg = "Response code to " + target.getUri() + " was " + response.getStatusInfo();
	    	throw new QuandlRuntimeException(msg);
	    }
	}
	
	
	protected QuandlSession(final String authToken) {
		this._authToken = authToken;
	}
	
	public String getAuthToken() {
		return _authToken;
	}
}
