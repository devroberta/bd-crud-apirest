package com.zallpy.bd_api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zallpy.bd_api_rest.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
