package com.silicus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.silicus.model.User;

@Service
public class UserServiceImpl implements UserService{

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
	public User findUser(String id) {
		User result = users.stream().filter(u -> u.getId().equals(id)).findFirst().get();
	    return result;
	}
}
