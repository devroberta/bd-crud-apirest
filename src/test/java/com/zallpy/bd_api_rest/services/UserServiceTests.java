package com.zallpy.bd_api_rest.services;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.repositories.UserDocumentsRepository;
import com.zallpy.bd_api_rest.repositories.UserRepository;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;
import com.zallpy.bd_api_rest.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;

	@Mock
	private UserDocumentsRepository docRepository;

	private long nonExistingId;
	private User user;
	private UserFullDTO userFullDTO;
	private List<User> list;
	private List<UserFullDTO> listDto;
	
	@BeforeEach
	void setUp() throws Exception {

		nonExistingId = 100L;
		user = Factory.createUser();
		userFullDTO = Factory.createUserDTO();
		list = new ArrayList<>(List.of(user));
		listDto = new ArrayList<>(List.of(userFullDTO));

		when(repository.findAll()).thenReturn(list);
		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		when(repository.save(any())).thenReturn(user);
		doNothing().when(docRepository).deleteById(any());
		doNothing().when(repository).deleteById(any());
		doThrow(EmptyResultDataAccessException.class).when(docRepository).deleteById(nonExistingId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
	}

	@Test
	public void findAllShouldReturnList() {
		List<UserFullDTO> result = service.findAll();
		assertNotNull(result);
		verify(repository).findAll();
	}

	@Test
	public void findByIdShouldReturnProductUserDTOWhenIdExists() {
		UserFullDTO result = service.findById(userFullDTO.getId());
		verify(repository).findById(result.getId());
	}

	@Test
	public void findByIdShouldReturnObjectEmptyWhenIdDoesNotExists() {
		assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}

	@Test
	public void saveShouldInsertUserReturnUserSaved() {
		UserFullDTO result = service.insert(userFullDTO);
		User user = new User(userFullDTO);
		assertNotNull(result.getId());
		assertEquals(1, result.getId());
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(1L);
		});

		verify(docRepository, times(1)).deleteById(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		verify(docRepository, times(1)).deleteById(nonExistingId);
	}
}
