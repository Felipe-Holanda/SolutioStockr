package com.techsolutio.solutiostockr.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsolutio.solutiostockr.dto.UsersDto;
import com.techsolutio.solutiostockr.models.Users;
import com.techsolutio.solutiostockr.services.UsersServices;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    
    private UsersServices usersServices;

    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersDto userData){
        final Users newUser = usersServices.createUser(userData);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> listUsers(){
        final List<Users> users = usersServices.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> retrieveUser(@PathVariable UUID id){
        final Users foundUser = usersServices.retrieveUser(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody UsersDto userData){
        final Users updatedUser = usersServices.updateUser(UUID.fromString(id), userData);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> togglePrivileges(@PathVariable String id){
        final Users updatedUser = usersServices.togglePrivileges(UUID.fromString(id));
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
