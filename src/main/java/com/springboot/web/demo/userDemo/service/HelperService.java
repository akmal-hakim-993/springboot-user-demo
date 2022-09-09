package com.springboot.web.demo.userDemo.service;

import com.springboot.web.demo.userDemo.model.User;

public abstract class HelperService {

	public static User updateRequired(User existingRec, User updateRec) {
		
		if(!updateRec.getUsername().isEmpty())
			existingRec.setUsername(updateRec.getUsername());
		if(!updateRec.getName().isEmpty())
			existingRec.setName(updateRec.getName());
		// dont update password wuthout roles access
//		if(!updateRec.getPassword().isEmpty())
//			existingRec.setPassword(updateRec.getPassword());
		
		return existingRec;
	}
}
