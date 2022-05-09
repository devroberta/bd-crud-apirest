package com.zallpy.bd_api_rest.repositories;

import com.zallpy.bd_api_rest.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByEmail(String email);
}