package com.api.rest.webservice;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.service.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerWeService {

	@Autowired
	private CustomerService cs;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public ResponseEntity<Map<String, String>> createCustomer(@RequestBody Map<String, String> json){
		Map<String,String> response = cs.create(json);
		
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pages/{page}/{size}")
	public ResponseEntity<Resources<Map<String,String>>> getCustomerPages(@PathVariable(name="page", required=false) Integer pageNum,
			@PathVariable(name="size", required=false) Integer size ){
		//PageResource<Map<String,String>> response = new PageResource(new HashMap<String,String>());
		Resources<Map<String,String>> results = null;
		if(null==pageNum & null==size) {
			List<Map<String,String>> response = cs.get(0, 0);
			results = new Resources<Map<String,String>>(response);
			results.add(linkTo(methodOn(CustomerWeService.class).getCustomerPages(pageNum, size)).withSelfRel());
		}
		
		return new ResponseEntity<Resources<Map<String,String>>>(results, HttpStatus.OK);
	}
	
	
}
