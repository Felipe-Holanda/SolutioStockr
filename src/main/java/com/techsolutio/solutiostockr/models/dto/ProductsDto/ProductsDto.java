package com.techsolutio.solutiostockr.models.dto.ProductsDto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class ProductsDto {
    
    @NotNull(message = "Product name can't be null")
    @NotBlank(message = "Product name can't be blank")
    private String productName;

    @NotNull(message = "Price can't be null")
    @NotBlank(message = "Price can't be blank")
    private float price;

    @NotNull(message = "Amount disposable can't be null")
    @NotBlank(message = "Amount disposable can't be blank")
    private int amountDisposable;

    @NotNull(message = "Vendor name can't be null")
    @NotBlank(message = "Vendor name can't be blank")
    @Length(min = 3, max = 64, message = "Vendor name must be between 3 and 64 characters")
    private String vendorName;

    @NotNull(message = "Vendor registration can't be null")
    @NotBlank(message = "Vendor registration can't be blank")
    @Length(min = 18, max = 18, message = "Vendor registration must be exactly 14 characters")
    @Pattern(regexp = "[0-9]{2}.[0-9]{3}.[0-9]{3}/[0-9]{4}-[0-9]{2}", message = "Vendor registration must be in the format 00.000.000/0000-00")
    private String vendorRegistration;

}
