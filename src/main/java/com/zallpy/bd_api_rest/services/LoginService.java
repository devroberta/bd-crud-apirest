package com.zallpy.bd_api_rest.services;

import com.zallpy.bd_api_rest.entities.Login;
import com.zallpy.bd_api_rest.repositories.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = repository.findByEmail(username);
        if (login == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return login;
    }
}
