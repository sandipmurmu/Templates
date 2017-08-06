package com.api.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="first_name")
	private String fisrtName;
	
	@JsonProperty(value="last_name")
	private String lastName;
	
	@JsonProperty(value="gender")
	private String gender;
	
	public Customer() {
		
	}
	
	public Customer(String id, String firstName, String lastName, String gender) {
		this.id = id;
		this.fisrtName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}


	public String getId() {
		return id;
	}


	public String getFisrtName() {
		return fisrtName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getGender() {
		return gender;
	}
	
	
}
