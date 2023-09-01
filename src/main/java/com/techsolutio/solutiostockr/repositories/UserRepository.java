package com.techsolutio.solutiostockr.repositories;

import com.techsolutio.solutiostockr.models.Users;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, UUID>{
    
}
