package com.techsolutio.solutiostockr.models.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techsolutio.solutiostockr.models.interfaces.UsersRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 18, unique = true)
    private String login;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(length = 14, nullable = false, unique = true)
    private String registration;

    @JsonIgnore
    private UsersRoles role;

    @CreatedDate
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    private Timestamp created_at = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp default now()")
    private Timestamp updated_at = new Timestamp(System.currentTimeMillis());

    public Users(){}

    public Users(String name, String login, String password, String registration, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.registration = registration;
        this.role = role.equals("ADMIN") ? UsersRoles.ADMIN : UsersRoles.USER;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    if (this.role == UsersRoles.ADMIN) {
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    return authorities;
}

    public UUID getId() {
        return id;
    }

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

    public boolean isAdmin() {
        return role == UsersRoles.ADMIN;
    }

    public void setAdmin(boolean isAdmin) {
        this.role = isAdmin ? UsersRoles.ADMIN : UsersRoles.USER;
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

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
