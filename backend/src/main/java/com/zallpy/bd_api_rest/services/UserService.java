package com.zallpy.bd_api_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.entities.UserDocuments;
import com.zallpy.bd_api_rest.repositories.UserDocumentsRepository;
import com.zallpy.bd_api_rest.repositories.UserRepository;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	private UserDocumentsRepository docRepository;
	
	@Transactional(readOnly = true)
	public List<UserFullDTO> findAll() {
		return userRepository.findAll().stream().map(x -> new UserFullDTO(x, docRepository.getById(x.getId())
				)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserFullDTO findById(Long id) {
		return new UserFullDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")),
				docRepository.getById(id));
	}

	public UserFullDTO insert(UserFullDTO obj) {
		return new UserFullDTO(userRepository.save(new User(obj)), docRepository.save(new UserDocuments(obj)));
	}

	public void delete(Long id) {
		try {
			docRepository.deleteById(id);
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}
	
	public UserFullDTO update(UserFullDTO obj) {
		try {
			return new UserFullDTO(userRepository.save(userRepository.getOne(obj.getId())), 
					docRepository.save(docRepository.getOne(obj.getId())));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(obj.getId());
		}
	}

}
