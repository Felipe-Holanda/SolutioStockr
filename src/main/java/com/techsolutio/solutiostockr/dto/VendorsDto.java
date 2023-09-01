package com.techsolutio.solutiostockr.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VendorsDto {

    @NotNull(message = "Vendor name cannot be null")
    @NotEmpty(message = "Vendor name cannot be empty")
    private String vendorName;

    @NotNull(message = "Registration cannot be null")
    @NotEmpty(message = "Registration cannot be empty")
    @Length(min = 14, max = 14, message = "Registration must be 14 characters length")
    private String registration;

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
}
