package com.ems.ems.dto;

import com.ems.ems.model.Role;

public class AuthResponseDTO {
    private String token;
    private String refreshToken;
    private String type;
    private Role role;


    public AuthResponseDTO(String token, String refreshToken, String type, Role role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.type = type;
        this.role=role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
