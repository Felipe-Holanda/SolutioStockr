package com.techsolutio.solutiostockr.models.dto;

import jakarta.validation.constraints.Size;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsersDto {
    
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Login cannot be blank")
    @Size(min = 6, max = 18, message = "login must be between 6 and 18 characters length")
    private String login;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters length")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @NotBlank(message = "Registration cannot be blank")
    @Size(min = 11, max = 11, message = "Registration must be 11 characters length")
    @Pattern(regexp = "^[0-9]*$", message = "Registration must contain only numbers")
    private String registration;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be ROLE_ADMIN or ROLE_USER")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role.toUpperCase();
    }

}
