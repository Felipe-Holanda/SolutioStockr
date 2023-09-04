package com.techsolutio.solutiostockr.models.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 64, name = "product_name")
    private String productName;

    @Column(nullable = false)
    private float price;

    @Column(columnDefinition = "integer default 0", name = "amount_disposable")
    private int amountDisposable;

    @Column(nullable = false, name = "vendor_name")
    private String vendorName;

    @Column(nullable = false, name = "vendor_registration")
    private String vendorRegistration;

    public Products() {
    }

    public Products(String productName, float price, int amountDisposable, String vendorName, String vendorRegistration) {
        this.productName = productName;
        this.price = price;
        this.amountDisposable = amountDisposable;
        this.vendorName = vendorName;
        this.vendorRegistration = vendorRegistration;
    }

    public UUID getId() {
        return id;
    }

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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorRegistration() {
        return vendorRegistration;
    }

    public void setVendorRegistration(String vendorRegistration) {
        this.vendorRegistration = vendorRegistration;
    }

}
