package com.springboot.web.demo.userDemo.util;

import com.springboot.web.demo.userDemo.dto.UserDto;
import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.model.UserRole;

public abstract class UserUtil {

	public static User updateRequired(User existingRec, User updateRec) {
		
		if(updateRec.getUsername() != null)
			existingRec.setUsername(updateRec.getUsername());
		if(updateRec.getName() != null)
			existingRec.setName(updateRec.getName());
		if(updateRec.getPassword() != null)
			existingRec.setPassword(updateRec.getPassword());
		
		return existingRec;
	}
	
	public static User dtoToUserMap(UserDto dto) {
		User user = new User();
		
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setUserRole(UserRole.USER);
		
		return user;
	}
}
