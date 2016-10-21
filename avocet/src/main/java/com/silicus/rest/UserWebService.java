package com.silicus.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.silicus.model.User;
import com.silicus.service.UserService;

@RestController
public class UserWebService {

	@Autowired
	private UserService userservice;
	
	
	@RequestMapping(
			value="/users/list", 
			method=RequestMethod.GET, 
			produces="application/json"
			)
	@ResponseBody
	public List<User> users(){
		return userservice.getUsers();
		
	}
}
