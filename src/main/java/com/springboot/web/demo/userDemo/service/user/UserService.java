package com.springboot.web.demo.userDemo.service.user;

import java.util.List;

import com.springboot.web.demo.userDemo.model.User;

public interface UserService {

	User findByUsername(String username);
	List<User> findAllUser();
	User createUser(User user);
	String updateUser(String username, User user);
	void deleteUser(String username);
	
}
