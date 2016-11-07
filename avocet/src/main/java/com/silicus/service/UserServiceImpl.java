package com.silicus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.silicus.model.User;

@Component
public class UserServiceImpl implements UserService {

	static List<User> users = new ArrayList<User>();
	
	static {
		users.add(createUser("1", "Johny Walker"));
		users.add(createUser("2", "Tom Hanks"));
	}
	
	private static User createUser(String id, String name){
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return this.users;
	}

	
	@Override
	public User login() {
		// TODO Auto-generated method stub
		return this.getUsers().get(0);
	}
	
}
