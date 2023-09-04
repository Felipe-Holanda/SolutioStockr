package com.techsolutio.solutiostockr.services;

import java.util.List;
import java.util.UUID;

import com.techsolutio.solutiostockr.models.dto.ProductsDto.ProductsDto;
import com.techsolutio.solutiostockr.models.entity.Products;

public interface ProductsServices {

    public Products createProduct(ProductsDto productData);

    public List<Products> listAllProducts();

    public Products retrieveProducts(UUID id);

    public Products updateProducts(UUID id, ProductsDto productData);

    public Products increaseStock(UUID id, ProductsDto productData);

    public void deleteProducts(UUID id);

}
