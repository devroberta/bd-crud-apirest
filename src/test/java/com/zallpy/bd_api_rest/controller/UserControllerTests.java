package com.zallpy.bd_api_rest.controller;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.repositories.UserRepository;
import com.zallpy.bd_api_rest.services.UserService;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;
import com.zallpy.bd_api_rest.tests.Factory;
import com.zallpy.bd_api_rest.tests.TokenUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserRepository repository;

    private ObjectMapper objectMapper;

    private UserFullDTO userDto;
    private Long nonExistingId;
    private List<UserFullDTO> list;

    @Autowired
    private TokenUtil tokenUtil;

    private String clientUsername;
    private String clientPassword;
    private String adminUsername;
    private String adminPassword;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        clientUsername = "roberta@gmail.com";
        clientPassword = "123456";
        adminUsername = "matheus@gmail.com";
        adminPassword = "123456";

        nonExistingId = 3L;
        userDto = Factory.createUserDTO();
        list = new ArrayList<>(List.of(userDto));

        when(service.findAll()).thenReturn(list);
        when(service.findById(anyLong())).thenReturn(userDto);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        when(service.update(any())).thenReturn(userDto);
    }

    @Test
    public void findAllShouldReturnList() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is(1)));
    }

    @Test
    public void findByIdShouldReturnUserWhenIdExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{id}", anyLong()).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnUserDTOWhenIdExists() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);

        String jsonBody = objectMapper.writeValueAsString(userDto);
        ResultActions result = mockMvc.perform(put("/users")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1L));
        result.andExpect(jsonPath("$.nome").value("Roberta"));
    }
}
