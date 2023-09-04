package com.techsolutio.solutiostockr.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.infra.security.TokenService;
import com.techsolutio.solutiostockr.models.dto.Auth.AuthenticationDto;
import com.techsolutio.solutiostockr.models.dto.Users.UsersDto;
import com.techsolutio.solutiostockr.models.dto.Users.UsersResponseDto;
import com.techsolutio.solutiostockr.models.entity.Users;
import com.techsolutio.solutiostockr.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody @Valid AuthenticationDto authData){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authData.getLogin(), authData.getPassword());
        var auth = this.authManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users)auth.getPrincipal());

        final HashMap<String, String> response = new HashMap<>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }

    @PostMapping("/register")
    public ResponseEntity<UsersResponseDto> register(@RequestBody @Valid UsersDto authData) throws AppException{
        if(userRepository.findByLogin(authData.getLogin()) != null) throw new AppException("Login already exists", HttpStatus.CONFLICT);
        if(userRepository.findByRegistration(authData.getRegistration()) != null) throw new AppException("Registration already exists", HttpStatus.CONFLICT);

        String encryptedPassword = new BCryptPasswordEncoder().encode(authData.getPassword());
        Users newUser = new Users(
            authData.getName(),
            authData.getLogin(),
            encryptedPassword,
            authData.getRegistration(),
            authData.getRole()
        );

        this.userRepository.save(newUser);

        final UsersResponseDto userResponse = new UsersResponseDto(
            newUser.getId(),
            newUser.getName(),
            newUser.getLogin(),
            newUser.getRegistration(),
            newUser.getCreated_at(),
            newUser.getUpdated_at()
        );
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDetails> profile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final UserDetails user = userRepository.findByLogin(userDetails.getUsername());
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
