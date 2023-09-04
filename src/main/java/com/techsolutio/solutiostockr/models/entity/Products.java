package com.techsolutio.solutiostockr.models.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendors vendor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Products() {
    }

    public Products(String productName, float price, int amountDisposable, Vendors vendor, Users user) {
        this.productName = productName;
        this.price = price;
        this.amountDisposable = amountDisposable;
        this.vendor = vendor;
        this.user = user;
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
