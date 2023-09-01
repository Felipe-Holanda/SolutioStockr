package com.techsolutio.solutiostockr.models;

import java.security.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Vendors {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 64, name = "vendor_name")
    private String vendorName;

    @Column(nullable = false, length = 14, unique = true)
    private String registration;

    @ManyToMany
    @Column(nullable = false, name = "registered_by")
    private Users registeredBy;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp default now()")
    private Timestamp updated_at;

    public Vendors() {}

    public Vendors(String vendorName, String registration, Users registeredBy) {
        this.vendorName = vendorName;
        this.registration = registration;
        this.registeredBy = registeredBy;
    }

    public UUID getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Users getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Users registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
