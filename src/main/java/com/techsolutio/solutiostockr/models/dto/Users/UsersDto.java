package com.techsolutio.solutiostockr.models.dto.Users;

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
    @Size(min = 8, max = 92, message = "Password must be between 8 and 92 characters length")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @NotBlank(message = "Registration cannot be blank")
    @Size(min = 14, max = 15, message = "Registration must be 14 characters length, including dots and hyphen")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "Registration must be on 000.000.000-00 format.")
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
