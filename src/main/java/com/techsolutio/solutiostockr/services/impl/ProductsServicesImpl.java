package com.techsolutio.solutiostockr.services.impl;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.techsolutio.solutiostockr.dto.ProductsDto;
import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.models.Products;
import com.techsolutio.solutiostockr.models.Users;
import com.techsolutio.solutiostockr.models.Vendors;
import com.techsolutio.solutiostockr.repositories.ProductsRepository;
import com.techsolutio.solutiostockr.services.ProductsServices;

public class ProductsServicesImpl implements ProductsServices {
    
    private ProductsRepository productsRepository;

    public ProductsServicesImpl(ProductsRepository productsRepository) {

        this.productsRepository = productsRepository;
    }

    public Products createProducts(ProductsDto productData, Vendors vendor, Users owner){
        
        final Products newProduct = new Products(
            productData.getProductName(),
            productData.getPrice(),
            productData.getAmountDisposable(),
            vendor,
            owner
        );

        return productsRepository.save(newProduct);
    }

    public List<Products> listProducts(){
        return productsRepository.findAll();
    }

    public Products retrieveProducts(UUID id){
        final Products product = productsRepository.findById(id).orElse(null);
        if(product == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        return product;
    }

    public Products updateProducts(UUID id, ProductsDto productData){
        final Products product = productsRepository.findById(id).orElse(null);
        if(product == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        product.setProductName(productData.getProductName());
        product.setPrice(productData.getPrice());
        return productsRepository.save(product);
    }

    public void deleteProducts(UUID id){
        final Products product = productsRepository.findById(id).orElse(null);
        if(product == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        productsRepository.delete(product);
    }

    public Products updateProductsAmount(UUID id, int amount){
        final Products product = productsRepository.findById(id).orElse(null);
        if(product == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        product.setAmountDisposable(amount);
        return productsRepository.save(product);
    }
}
