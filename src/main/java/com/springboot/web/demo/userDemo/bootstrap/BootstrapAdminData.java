package com.springboot.web.demo.userDemo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.model.UserRole;
import com.springboot.web.demo.userDemo.service.user.UserService;

@Component
public class BootstrapAdminData implements CommandLineRunner{

	private UserService userservice;
    
    public BootstrapAdminData(UserService userService) {
		this.userservice = userService;
    }
	/**
	 * create admin
	 */
	@Override
	public void run(String... args) throws Exception {
		
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setName("Admin");
		admin.setUserRole(UserRole.ADMIN);
		userservice.createUser(admin);
		System.out.println("Admin created. username = admin , password = admin");
	}

}
