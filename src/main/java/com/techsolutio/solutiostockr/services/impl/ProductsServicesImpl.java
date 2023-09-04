package com.techsolutio.solutiostockr.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techsolutio.solutiostockr.exceptions.AppException;
import com.techsolutio.solutiostockr.models.dto.ProductsDto.ProductsDto;
import com.techsolutio.solutiostockr.models.entity.Products;
import com.techsolutio.solutiostockr.repositories.ProductsRepository;
import com.techsolutio.solutiostockr.services.ProductsServices;

@Service
public class ProductsServicesImpl implements ProductsServices {
    
    @Autowired
    private ProductsRepository productsRepository;

    public Products createProduct(ProductsDto productData){
        Products newProduct = new Products(
            productData.getProductName(),
            productData.getPrice(),
            productData.getAmountDisposable(),
            productData.getVendorName(),
            productData.getVendorRegistration()
        );

        return productsRepository.save(newProduct);
    }

    public List<Products> listAllProducts(){
        return productsRepository.findAll();
    }

    public Products retrieveProducts(UUID id) throws AppException{
        final Products foundProduct = productsRepository.findById(id).orElse(null);
        if(foundProduct == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        return foundProduct;
    }

    public Products updateProducts(UUID id, ProductsDto productData) throws AppException{
        final Products foundProduct = productsRepository.findById(id).orElse(null);
        if(foundProduct == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);

        if(productData.getProductName() != null) foundProduct.setProductName(productData.getProductName());
        if(productData.getPrice() != 0) foundProduct.setPrice(productData.getPrice());
        if(productData.getAmountDisposable() != 0) foundProduct.setAmountDisposable(productData.getAmountDisposable());
        if(productData.getVendorName() != null) foundProduct.setVendorName(productData.getVendorName());
        if(productData.getVendorRegistration() != null) foundProduct.setVendorRegistration(productData.getVendorRegistration());

        return productsRepository.save(foundProduct);
    }

    public Products increaseStock(UUID id, ProductsDto productData) throws AppException{
        final Products foundProduct = productsRepository.findById(id).orElse(null);
        if(foundProduct == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);

        if(productData.getAmountDisposable() != 0) foundProduct.setAmountDisposable(foundProduct.getAmountDisposable() + productData.getAmountDisposable());

        return productsRepository.save(foundProduct);
    }

    public void deleteProducts(UUID id) throws AppException{
        final Products foundProduct = productsRepository.findById(id).orElse(null);
        if(foundProduct == null) throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        productsRepository.delete(foundProduct);
    }
}
