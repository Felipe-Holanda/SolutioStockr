package com.techsolutio.solutiostockr.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsolutio.solutiostockr.models.entity.Vendors;

public interface VendorRepository extends JpaRepository<Vendors, UUID>{
    
}
