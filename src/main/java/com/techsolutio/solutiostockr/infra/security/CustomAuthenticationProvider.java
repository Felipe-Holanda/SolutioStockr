package com.techsolutio.solutiostockr.infra.security;

import com.techsolutio.solutiostockr.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techsolutio.solutiostockr.repositories.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    UserDetails user = userRepository.findByLogin(username);

    if (user == null) {
        throw new AppException("Wrong user or password", HttpStatus.UNAUTHORIZED);
    }

    // Use o BCrypt para verificar a senha
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new AppException("Wrong user or password", HttpStatus.UNAUTHORIZED);
    }

    return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
}
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}