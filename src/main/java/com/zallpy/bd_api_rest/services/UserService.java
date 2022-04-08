package com.zallpy.bd_api_rest.services;

import com.zallpy.bd_api_rest.dto.UserDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserRepository;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

  private UserRepository repository;

  @Transactional(readOnly = true)
  public List<UserDTO> findAll() {
    return repository.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    return new UserDTO(repository.findById(id);.
    orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")););
  }

  public UserDTO insert(UserDTO obj) {
    return new UserDTO(repository.save(new User(obj)));
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id não encontrado " + id);
    }
  }

  public UserDTO update(UserDTO obj) {
    try {
      return new UserDTO(repository.save(repository.getOne(obj.getId())));
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(obj.getId());
    }
  }

}
