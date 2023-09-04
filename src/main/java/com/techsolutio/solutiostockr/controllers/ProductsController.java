package com.techsolutio.solutiostockr.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.models.dto.ProductsDto.ProductsDto;
import com.techsolutio.solutiostockr.models.entity.Products;
import com.techsolutio.solutiostockr.services.ProductsServices;

import jakarta.validation.Valid;

//


@RestController
@RequestMapping("/api/products")
public class ProductsController {
   
    @Autowired
    private ProductsServices productsServices;

    @GetMapping
    public ResponseEntity<List<Products>> listAllProducts(){
        return new ResponseEntity<>(productsServices.listAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> retrieveProducts(@PathVariable String id){
        try {

            final UUID productId = UUID.fromString(id);
            return new ResponseEntity<>(productsServices.retrieveProducts(productId), HttpStatus.OK);

        } catch (Exception e) { throw new AppException("Product not found", HttpStatus.NOT_FOUND);}
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody @Valid ProductsDto productData){
        return new ResponseEntity<>(productsServices.createProduct(productData), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id, @RequestBody ProductsDto productData){
        try {
            final UUID productId = UUID.fromString(id);
            return new ResponseEntity<>(productsServices.updateProducts(productId, productData), HttpStatus.OK);

        } catch (Exception e) { throw new AppException("Product not found", HttpStatus.NOT_FOUND);}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> increaseStock(@PathVariable String id, @RequestBody ProductsDto productData){
        try {

            final UUID productId = UUID.fromString(id);
            return new ResponseEntity<>(productsServices.increaseStock(productId, productData), HttpStatus.OK);

        } catch (Exception e) { throw new AppException("Product not found", HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProduct(@PathVariable String id){
        try {

            final UUID productId = UUID.fromString(id);
            productsServices.deleteProducts(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) { throw new AppException("Product not found", HttpStatus.NOT_FOUND);}
    }

}
