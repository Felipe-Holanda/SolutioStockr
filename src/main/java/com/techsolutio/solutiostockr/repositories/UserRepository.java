package com.techsolutio.solutiostockr.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.techsolutio.solutiostockr.models.entity.Users;

public interface UserRepository extends JpaRepository<Users, UUID>{
 
    @Query("SELECT u FROM Users u WHERE u.login = :login")
    UserDetails findByLogin(@Param("login") String login);

    Users findByRegistration(String registration);
    
}
