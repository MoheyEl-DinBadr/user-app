package com.example.dto;

public class UserDTO {
    private final String id;
    private final String accessToken;

    public UserDTO(String id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
