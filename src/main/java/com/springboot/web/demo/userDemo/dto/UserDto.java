package com.springboot.web.demo.userDemo.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.springboot.web.demo.userDemo.model.UserRole;

public class UserDto {

	private String username;
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	private Boolean locked;
	private Boolean enabled;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
