package com.techsolutio.solutiostockr.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsolutio.solutiostockr.models.dto.ProductsDto;
import com.techsolutio.solutiostockr.models.entity.Products;
import com.techsolutio.solutiostockr.models.entity.Users;
import com.techsolutio.solutiostockr.models.entity.Vendors;
import com.techsolutio.solutiostockr.repositories.UserRepository;
import com.techsolutio.solutiostockr.services.ProductsServices;
import com.techsolutio.solutiostockr.services.VendorServices;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductsServices productsServices;
    private VendorServices vendorServices;
    private UserRepository userRepository;

    public ProductsController(ProductsServices productsServices) {
        this.productsServices = productsServices;
    }

    @PostMapping
    public ResponseEntity<Products> createProducts(@RequestBody ProductsDto productData, @RequestAttribute(name = "vendorID") String vendorID){
        final UUID ownerID = new UUID(1, 999);
        final Users owner = userRepository.findById(ownerID).orElse(null);
        final Vendors vendor = vendorServices.retrieveVendors(UUID.fromString(vendorID));
        final Products newProduct =  productsServices.createProducts(productData, vendor, owner);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Products>> listAllProducts(){
        final List<Products> products = productsServices.listProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> retrieveProducts(@RequestAttribute(name = "vendorID") String vendorID, @RequestAttribute(name = "productID") String productID){
        final Products product = productsServices.retrieveProducts(UUID.fromString(productID));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProducts(@RequestBody ProductsDto productData, @RequestAttribute(name = "productID") String productID){
        final Products product = productsServices.updateProducts(UUID.fromString(productID), productData);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProducts(@PathVariable String productID){
        productsServices.deleteProducts(UUID.fromString(productID));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
