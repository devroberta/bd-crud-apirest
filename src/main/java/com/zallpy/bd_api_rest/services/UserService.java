package com.zallpy.bd_api_rest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zallpy.bd_api_rest.dto.UserDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserRepository;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		List<User> list = repository.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserDTO obj) {
		User entity = new User();
		entity.setNome(obj.getNome());
		entity.setSobrenome(obj.getSobrenome());
		entity.setEmail(obj.getEmail());
		entity.setIdade(obj.getIdade());
		entity.setAtivo(obj.getAtivo());
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO obj) {
		try {
			User entity = repository.getOne(id);
			entity.setNome(obj.getNome());
			entity.setSobrenome(obj.getSobrenome());
			entity.setEmail(obj.getEmail());
			entity.setIdade(obj.getIdade());
			entity.setAtivo(obj.getAtivo());
			entity = repository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}
