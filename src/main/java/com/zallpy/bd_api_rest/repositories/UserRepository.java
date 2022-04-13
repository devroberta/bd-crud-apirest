package com.zallpy.bd_api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zallpy.bd_api_rest.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
