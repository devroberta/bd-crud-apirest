package com.zallpy.bd_api_rest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.entities.UserDocuments;
import com.zallpy.bd_api_rest.entities.enums.OrgaoEmissor;
import com.zallpy.bd_api_rest.entities.enums.States;
import com.zallpy.bd_api_rest.repositories.UserDocumentsRepository;
import com.zallpy.bd_api_rest.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDocumentsRepository docRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		docRepository.deleteAll();
		
		User user1 = new User(null,"Maria", "Silva", "maria@gmail.com", 34, true);
		User user2 = new User(null,"João", "Oliveira", "joao@gmail.com", 39, true);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		UserDocuments userDocs1 = new UserDocuments(user1.getId(), "10101010", OrgaoEmissor.SJS, States.RIO_GRANDE_DO_SUL, "010.010.010-001", "1111111111", user1);
		UserDocuments userDocs2 = new UserDocuments(user2.getId(), "20202020", OrgaoEmissor.SSP, States.RIO_GRANDE_DO_SUL, "020.020.020-002", "2222222222", user2);
		
		docRepository.saveAll(Arrays.asList(userDocs1, userDocs2));
		
		UserFullDTO cadastro1 = new UserFullDTO(user1, userDocs1);
		UserFullDTO cadastro2 = new UserFullDTO(user2, userDocs2);
		
		
	}
	

}
