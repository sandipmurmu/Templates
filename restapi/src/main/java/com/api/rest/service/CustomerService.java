package com.api.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.rest.representation.Customer;
import com.api.rest.representation.Page;
import com.api.rest.representation.PageLinks;

@Service
public class CustomerService {

	
	public void create(Customer customerData) throws Exception{
		
	}
	
	public Customer getCustomer(Integer id) {
		
		return new Customer();
	}
	
	
	public Page<List<Customer>> getPage(Integer pageNum) throws Exception{
		Customer c1 = new Customer("2", "sandip", "murmu", "male");
		Customer c2 = new Customer("1", "nicole", "kelly", "female");
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		Page<List<Customer>> page = new Page<>();
		
		return page;
	}
	
	
	public PageLinks<List<Customer>> getList() throws Exception{
		Customer c1 = new Customer("2", "sandip", "murmu", "male");
		Customer c2 = new Customer("1", "nicole", "kelly", "female");
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		
		PageLinks<List<Customer>> pageCustomers = new PageLinks<>(customers,3);
		
		return pageCustomers;
	}
	
	
}
