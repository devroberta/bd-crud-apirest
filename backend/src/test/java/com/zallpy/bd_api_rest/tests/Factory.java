package com.zallpy.bd_api_rest.tests;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;

public class Factory {
	
	public static User createUser() {		
		User user = new User(1L, "Roberta", "de Siqueira", "designer.roberta@gmail.com", 38, true);
		return user;
	}
	
	public static UserFullDTO createUserDTO() {
		User user = createUser();
		return new UserFullDTO(user);
	}

}
