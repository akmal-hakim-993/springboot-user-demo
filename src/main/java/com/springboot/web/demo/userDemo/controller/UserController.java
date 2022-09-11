package com.springboot.web.demo.userDemo.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Controller
@RequestMapping("/api/user")
public class UserController {

	private UserService userservice;
    
    public UserController(UserService userService) {
    	this.userservice = userService;
    }
     
    /**
     * 
     * End points
     */
    @GetMapping("/all")
    public List<User> findAllUsers() {
         return userservice.findAllUser();
    }
 
    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable(value = "username") String username){
        Optional<User> user = Optional.ofNullable(userservice.findByUsername(username));
        
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") UserDto dto) {
    	if(dto.getUsername().isBlank() || dto.getPassword().isBlank()) return "redirect:/registration?error";
		User user = UserUtil.dtoToUserMap(dto);
		userservice.createUser(user);
		
    	return "redirect:/registration?success";
    }
    
    @PutMapping("/{username}")
    public String updateUser(@PathVariable( "username" ) String username, @RequestBody UserDto dto) {	
    	User user = UserUtil.dtoToUserMap(dto);
    	
    	return userservice.updateUser(username, user);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username) {
    	return userservice.deleteUser(username);
    }
    
    /*
     * Show form
     */
    
    @GetMapping("/signup")
    public String showRegistrationForm() {
    	return "registration";
    }
    
    /**
     * Model Attribute
     */
    
    @ModelAttribute("user")
    public UserDto userDto() {
    	return new UserDto();
    }
}
