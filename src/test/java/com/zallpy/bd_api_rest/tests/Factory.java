package com.zallpy.bd_api_rest.tests;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.entities.UserDocuments;
import com.zallpy.bd_api_rest.entities.enums.OrgaoEmissor;
import com.zallpy.bd_api_rest.entities.enums.States;

public class Factory {
	
	public static User createUser() {
		User user = new User(1L, "Roberta", "de Siqueira", "designer.roberta@gmail.com", 38, true);
		UserDocuments userDocs1 = new UserDocuments(1L, "90909090", OrgaoEmissor.SSP, States.DISTRITO_FEDERAL, "999.999.999-99", "9999999999", user);
		user.setDocuments(userDocs1);
		return user;
	}


	public static UserFullDTO createUserDTO() {
		return new UserFullDTO(createUser());
	}
}
