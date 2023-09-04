package com.techsolutio.solutiostockr.models.dto.ProductsDto;

import com.techsolutio.solutiostockr.models.entity.Users;
import com.techsolutio.solutiostockr.models.entity.Vendors;

public class ProductsDto {
    
    private String productName;

    private float price;

    private int amountDisposable;

    private Vendors vendor;

    private Users user;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmountDisposable() {
        return amountDisposable;
    }

    public void setAmountDisposable(int amountDisposable) {
        this.amountDisposable = amountDisposable;
    }

    public Vendors getVendor() {
        return vendor;
    }

    public void setVendor(Vendors vendor) {
        this.vendor = vendor;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


}
