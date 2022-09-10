package com.springboot.web.demo.userDemo.util;

import com.springboot.web.demo.userDemo.dto.UserDto;
import com.springboot.web.demo.userDemo.model.User;
import com.springboot.web.demo.userDemo.model.UserRole;

public abstract class UserUtil {

	public static User updateRequired(User existingRec, User updateRec) {
		
		if(!updateRec.getUsername().isEmpty())
			existingRec.setUsername(updateRec.getUsername());
		if(!updateRec.getName().isEmpty())
			existingRec.setName(updateRec.getName());
		if(!updateRec.getPassword().isEmpty())
			existingRec.setPassword(updateRec.getPassword());
		
		return existingRec;
	}
	
	public static User nullCheck(User user) {
		
		if(user.getName() == null)
			user.setName("");
		if(user.getUsername() == null)
			user.setName("");
		if(user.getPassword() == null)
			user.setName("");
		
		return user;
	}
	
	public static User dtoToUserMap(UserDto dto) {
		User user = new User();
		
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setUserRole(UserRole.USER);
		
		nullCheck(user);
		
		return user;
	}
}
