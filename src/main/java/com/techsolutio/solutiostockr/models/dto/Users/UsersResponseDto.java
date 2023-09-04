package com.techsolutio.solutiostockr.models.dto.Users;

import java.util.UUID;
import java.sql.Timestamp;

public class UsersResponseDto {
    private UUID id;

    private String name;

    private String login;

    private String registration;


    private Timestamp createdAt;

    private Timestamp updatedAt;

    public UsersResponseDto() {
    }

    public UsersResponseDto(UUID id, String name, String login, String registration, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.registration = registration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }



}
