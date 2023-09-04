package com.techsolutio.solutiostockr.models.dto.Users;

import java.util.UUID;

import com.google.protobuf.Timestamp;

public class UsersResponseDto {
    private UUID id;

    private String name;

    private String login;

    private String registration;

    private String role;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
