package com.techsolutio.solutiostockr.services.impl;

import com.techsolutio.solutiostockr.dto.UsersDto;
import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.models.Users;
import com.techsolutio.solutiostockr.repositories.UserRepository;
import com.techsolutio.solutiostockr.services.UsersServices;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

public class UsersServicesImpl implements UsersServices{
    private UserRepository usersRepository;

    public UsersServicesImpl(UserRepository userRepository) {
        this.usersRepository = userRepository;
    }

    public Users createUser(UsersDto userData){
        final Users newUser = new Users(
            userData.getName(),
            userData.getEmail(),
            userData.getPassword(),
            userData.getRegistration()
        );

        return usersRepository.save(newUser);
    }

    public List<Users> listUsers(){
        return usersRepository.findAll();
    }

    public Users retrieveUser(UUID id){
        final Users foundUser = usersRepository.findById(id).orElse(null);

        if(foundUser == null) throw new AppException("User not found", HttpStatus.NOT_FOUND);

        return foundUser;

    }

    public Users updateUser(UUID id, UsersDto userData){
        final Users foundUser = usersRepository.findById(id).orElse(null);

        if(foundUser == null) throw new AppException("User not found", HttpStatus.NOT_FOUND);

        foundUser.setName(userData.getName());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setRegistration(userData.getRegistration());

        return usersRepository.save(foundUser);
        
    }

    public void deleteUser(UUID id){
        final Users foundUser = usersRepository.findById(id).orElse(null);

        if(foundUser == null) throw new AppException("User not found", HttpStatus.NOT_FOUND);

        usersRepository.delete(foundUser);
    }

    public Users togglePrivileges(UUID id){
        final Users foundUser = usersRepository.findById(id).orElse(null);

        if(foundUser == null) throw new AppException("User not found", HttpStatus.NOT_FOUND);

        foundUser.setAdmin(!foundUser.isAdmin());

        return usersRepository.save(foundUser);
    }

}
