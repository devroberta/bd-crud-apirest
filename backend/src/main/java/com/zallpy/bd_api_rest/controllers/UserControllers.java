package com.zallpy.bd_api_rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserControllers {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/cad")
	public List<User>findAll(User user) {
		return userRepository.findAll();
	}
	
}
