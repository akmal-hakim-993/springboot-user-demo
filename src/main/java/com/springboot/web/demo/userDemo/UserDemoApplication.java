package com.springboot.web.demo.userDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class UserDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDemoApplication.class, args);
	}

}
