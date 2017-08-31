package com.api.rest.util;

import com.api.rest.exception.SentifiException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private JsonUtil() {
		
	}
	
	public static JsonNode getData(String json, String name) {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataNode = null;
		try {
			if(null==json) {
				throw new Exception("no json found");
			}
			JsonNode node = mapper.readTree(json);
			 dataNode = node.findValue(name);
		} catch ( Exception e) {
			String msg = "Error occurred while processing json result";
			throw new SentifiException(msg,e);
		}
		
		return dataNode;
	}
}
