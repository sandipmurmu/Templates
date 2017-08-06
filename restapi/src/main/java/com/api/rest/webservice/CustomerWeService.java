package com.api.rest.webservice;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.representation.Customer;
import com.api.rest.representation.Page;
import com.api.rest.representation.PageLinks;
import com.api.rest.service.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerWeService {

	@Autowired
	private CustomerService cs;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public ResponseEntity<Void> createCustomer(@RequestBody Customer json) throws Exception{
		 cs.create(json);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable(value="id") Integer id){
		Customer response = cs.getCustomer(id);
		
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public ResponseEntity<Resources<Customer>> getCustomerList() throws Exception{
		
		PageLinks<List<Customer>> resultPage = cs.getList();
		List<Customer> results = resultPage.getResult();
		int pages = resultPage.getPages();
		Resources<Customer> resources = new Resources<>(results);
		
		//links from 2nd page onwards
		for(int i=2;i<=pages; i++) { 
			resources.add(linkTo(methodOn(CustomerWeService.class).getPage(i)).withRel("pages"));
		
		}
		
		return new ResponseEntity<Resources<Customer>>(resources, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pages/{page}")
	public ResponseEntity<Page<List<Customer>>> getPage(@PathVariable(value="page") Integer page) throws Exception{
		
		Page<List<Customer>> result = cs.getPage(page);
		return new ResponseEntity<Page<List<Customer>>>(result, HttpStatus.OK);
	}
	
	
}
