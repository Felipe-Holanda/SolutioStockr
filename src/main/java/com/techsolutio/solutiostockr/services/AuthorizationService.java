package com.techsolutio.solutiostockr.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizationService {
    public UserDetails loadUserByUsername(String login);
}
