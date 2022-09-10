package com.springboot.web.demo.userDemo.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.demo.userDemo.dto.UserDto;
import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.service.user.UserService;
import com.springboot.web.demo.userDemo.util.UserUtil;

@RestController
@Transactional
@RequestMapping("/api/user")
class UserController {

	private UserService userservice;
    
    public UserController(UserService userService) {
    	this.userservice = userService;
    }
        
    @GetMapping("/all")
    public List<User> findAllUsers() {
         return userservice.findAllUser();
    }
 
    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable(value = "username") String username) {
        Optional<User> user = Optional.ofNullable(userservice.findByUsername(username));
        
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/create")
    public User createUser(@RequestBody UserDto dto) {
    	if(dto.getUsername().isBlank() || dto.getPassword().isBlank()) return new User();
		User user = UserUtil.dtoToUserMap(dto);
		
    	return userservice.createUser(user);
    }
    
    @PutMapping(value = "/{username}")
    public String updateUser(@PathVariable( "username" ) String username, @RequestBody UserDto dto) {	
    	User user = UserUtil.dtoToUserMap(dto);
    	
    	return userservice.updateUser(username, user);
    }

    @DeleteMapping(value = "/{username}")
    public String deleteUser(@PathVariable("username") String username) {
    	return userservice.deleteUser(username);
    }
    
}
