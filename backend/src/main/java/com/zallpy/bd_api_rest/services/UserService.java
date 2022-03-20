package com.zallpy.bd_api_rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
			User entity = repository.findById(id).get();
			entity.setNome(obj.getNome());
			entity.setSobrenome(obj.getSobrenome());
			entity.setEmail(obj.getEmail());
			entity.setAtivo(obj.getAtivo());
			return repository.save(entity);
	}

}
