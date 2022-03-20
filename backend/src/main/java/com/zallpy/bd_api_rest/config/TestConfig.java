package com.zallpy.bd_api_rest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Roberta", "de Siqueira", "designer.roberta@gmail.com", 38, true);
		User u2 = new User(null, "Getulio", "da Motta", "getuliomotta@gmail.com", 40, true);
		User u3 = new User(null, "Jose", "Oliveira", "joseoliveira@gmail.com", 37, true);
		User u4 = new User(null, "Maria", "da Silva", "mariadasilva@gmail.com", 58, false);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
	}
}
