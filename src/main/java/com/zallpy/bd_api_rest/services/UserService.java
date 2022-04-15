package com.zallpy.bd_api_rest.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.entities.UserDocuments;
import com.zallpy.bd_api_rest.entities.enums.OrgaoEmissor;
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
				User user = new User(obj);
				UserDocuments docsUser = new UserDocuments(obj.getId(), obj.getRg(), obj.getOrgaoEmissor(), obj.getEstado(), obj.getCpf(), obj.getSus(), user);
				user.setDocuments(docRepository.save(docsUser));
				return new UserFullDTO(userRepository.save(user));
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
		return new UserFullDTO(userRepository.save(userRepository.findById(obj.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")).updateData(obj)));
	}

	public String validarCadastro(UserFullDTO obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserFullDTO>> constraintViolations = validator.validate(obj);
		String msgError = "";
		for (ConstraintViolation error : constraintViolations) {
			msgError = error.getMessage();
		}
		return msgError;
	}
}
