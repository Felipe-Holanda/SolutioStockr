package com.techsolutio.solutiostockr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsersDto {
    
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    @Size(min = 10, max = 92, message = "Email must be between 10 and 92 characters length")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters length")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @NotNull(message = "Registration cannot be null")
    @Size(min = 11, max = 11, message = "Registration must be 11 characters length")
    @Pattern(regexp = "^[0-9]*$", message = "Registration must contain only numbers")
    private String registration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

}
