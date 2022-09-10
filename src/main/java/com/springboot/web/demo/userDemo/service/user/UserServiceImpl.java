package com.springboot.web.demo.userDemo.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.repository.UserRepository;
import com.springboot.web.demo.userDemo.util.UserUtil;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
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
    		
    		String encodedPassword = bCryptPasswordEncoder
                    .encode(user.getPassword());
    		
    		user.setPassword(encodedPassword);
    		
    		return userRepository.save(user);
    	}
	}
	
	@Override
	public String updateUser(String username, User user) {
		Optional<User> savedUser = Optional.ofNullable(userRepository.findByUsername(username));
    	
    	if(savedUser.isPresent()) {
			UserUtil.updateRequired(savedUser.get(), user);
    		userRepository.save(savedUser.get());
            return "user "+username+" has been updated";
    	}
    	else {
    		return "user "+username+" does not exist. Aborting update";
    	}
	}
	
	@Override
	public String deleteUser(String username) {
    	Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
    	if(user.isPresent()){
    		userRepository.deleteByUsername(username);
        	return "user "+ username +" has been deleted";
    	}
    	else
    		return "user "+username+" does not exist. Aborting deletion";
		
	}
}
