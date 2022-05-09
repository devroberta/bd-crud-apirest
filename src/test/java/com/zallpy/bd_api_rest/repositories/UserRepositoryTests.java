package com.zallpy.bd_api_rest.repositories;

import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;
import com.zallpy.bd_api_rest.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private int totalUsers;
    private User user;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        totalUsers = 2;
        user = Factory.createUser();
    }

    @Test
    public void findAllShouldReturnAllList() {
        assertEquals(totalUsers, repository.findAll().size());
    }

    @Test
    public void findByIdShouldReturnObjectWhenIdExists() {
        assertTrue(repository.findById(existingId).isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist() {
        Optional<User> result = repository.findById(nonExistingId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void saveShouldUpdateWhenIdExists() {
        user = repository.getOne(existingId);
        user = repository.save(user);
        assertTrue(repository.findById(user.getId()).isPresent());
    }

    @Test
    public void saveShouldNotUpdateWhenIdNotExists() {
        user = repository.getOne(nonExistingId);
        assertFalse(repository.findById(user.getId()).isPresent());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        user.setId(null);
        user = repository.save(user);
        assertNotNull(user.getId());
        assertEquals(totalUsers+1, user.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        assertFalse(repository.findById(existingId).isPresent());
    }

    @Test
    public void deleteShouldReturnThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }


}
