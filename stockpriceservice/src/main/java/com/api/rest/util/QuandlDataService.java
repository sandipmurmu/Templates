package com.api.rest.util;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import com.api.rest.exception.SentifiException;

public final class QuandlDataService {

	  
	  private static final UriBuilder API_BASE_URL_V3 = UriBuilder.fromPath("https://www.quandl.com/api/v3");
	  
	  private static final String API_KEY_PARAM_NAME= "api_key";
	  
	  
	  private QuandlSession _session;
	  
	  private QuandlDataService(QuandlSession session) {
		  this._session = session;
	  }
	  
	  
	  public static QuandlDataService createSession(String authToken) {
		  if(null==authToken) {
			  throw new SentifiException("no input token");
		  }
		  return new QuandlDataService(new QuandlSession(authToken));
	  }
	  
	 
	  
	  public String getData(QuandlRequest request) throws IOException{
		  Client client = ClientBuilder.newClient();
		  WebTarget target = client.target(API_BASE_URL_V3);
		  target = request.appendPathAndQueryParameter(target);
		  target = target.queryParam(API_KEY_PARAM_NAME, _session.getAuthToken());
		  String response = null;
		  
		  response = _session.getJson(target);
		  
		  return response;
	  }
	  
	  
	  
  
}
