package com.api.rest.webservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.service.CustomerService;

@RestController
public class CustomerWeService {

	@Autowired
	private CustomerService cs;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/customers/")
	public ResponseEntity<Map<String, String>> createCustomer(@RequestBody Map<String, String> json){
		Map<String,String> response = cs.create(json);
		
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	}
	
	
	
}
