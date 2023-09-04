package com.techsolutio.solutiostockr.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.repositories.UserRepository;
import com.techsolutio.solutiostockr.services.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String login) throws AppException{
        UserDetails user = userRepository.findByLogin(login);
        if(user == null) throw new AppException("User not founded.", HttpStatus.NOT_FOUND);
        return user;
    }
}
