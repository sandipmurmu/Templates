package com.api.rest.quandl;

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
	
	public String getJson(final WebTarget target){
		Builder requestBuilder = target.request();
	    Response response = requestBuilder.buildGet().invoke();
	    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	    	InputStream inputStream = response.readEntity(InputStream.class);
	    	try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
	    		return reader.lines().collect(Collectors.joining("\n"));
	    	} catch (IOException e) {
				String msg = "IO Exception while getting json from Quandl";
				throw new QuandlRuntimeException(msg,e);
			} 
	     }else if(response.getStatus()== Response.Status.NOT_FOUND.getStatusCode()) {
	    	 //TODO: generate json for 404
	    	 String msg = "Response code of " + target.getUri() + " has " + "status  " +response.getStatus()+ " ,info " +response.getStatusInfo();
		     throw new QuandlRuntimeException(msg);
	     }else {
	    	
	    	String msg = "Response code of " + target.getUri() + " has " + "status  " +response.getStatus()+ " ,info " +response.getStatusInfo();
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
