package com.techsolutio.solutiostockr.services;

import java.util.UUID;
import java.util.List;

import com.techsolutio.solutiostockr.models.dto.ProductsDto;
import com.techsolutio.solutiostockr.models.entity.Products;
import com.techsolutio.solutiostockr.models.entity.Users;
import com.techsolutio.solutiostockr.models.entity.Vendors;

public interface ProductsServices {
    
    public Products createProducts(ProductsDto productData, Vendors vendor, Users owner);

    public List<Products> listProducts();

    public Products retrieveProducts(UUID id);

    public Products updateProducts(UUID id, ProductsDto productData);

    public void deleteProducts(UUID id);

    public Products updateProductsAmount(UUID id, int amount);
}
