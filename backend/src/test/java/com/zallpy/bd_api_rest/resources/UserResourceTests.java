package com.zallpy.bd_api_rest.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.zallpy.bd_api_rest.dto.UserDTO;
import com.zallpy.bd_api_rest.resouces.UserResource;
import com.zallpy.bd_api_rest.services.UserService;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;
import com.zallpy.bd_api_rest.tests.Factory;

@WebMvcTest(UserResource.class)
public class UserResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	private Long existingId;
	private Long nonExistingId;
	private UserDTO userDto;
	private List<UserDTO> listDto;
	
	@BeforeEach	
	void setUp() throws Exception {
		 
		existingId = 1L;
		nonExistingId = 2L;
		
		userDto = Factory.createUserDTO();
		listDto = new ArrayList<>(List.of(userDto));
		
		when(service.findAll()).thenReturn(listDto);
		when(service.findById(existingId)).thenReturn(userDto);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
	}

	@Test
	public void findAllShouldReturnList() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}
	
	@Test
	public void findByIdShouldReturnUserWhenIdExists() throws Exception {		
		ResultActions result = mockMvc.perform(get("/users/{id}", existingId).accept(MediaType.APPLICATION_JSON));		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/users/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));		
		result.andExpect(status().isNotFound());
	}
}
