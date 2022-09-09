package com.springboot.web.demo.userDemo.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.repository.UserRepository;
import com.springboot.web.demo.userDemo.service.HelperService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public User createUser(User user) {
    	Optional<User> savedUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername())); 
    	
    	if(savedUser.isPresent()) {
    		return savedUser.get();
    	}
    	else {
    		return userRepository.save(user);
    	}
	}
	
	@Override
	public String updateUser(String username, User user) {
		Optional<User> savedUser = Optional.ofNullable(userRepository.findByUsername(username));
    	
    	if(savedUser.isPresent()) {
			HelperService.updateRequired(savedUser.get(), user);
    		userRepository.save(savedUser.get());
            return "user "+username+" has been updated";
    	}
    	else {
    		return "user "+username+" does not exist. Aborting update";
    	}
	}
	
	@Override
	public void deleteUser(String username) {
		userRepository.deleteByUsername(username);
	}
}
