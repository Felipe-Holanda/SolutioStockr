package com.techsolutio.solutiostockr.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsolutio.solutiostockr.models.entity.Products;


public interface ProductsRepository extends JpaRepository<Products, UUID>{
    
}
