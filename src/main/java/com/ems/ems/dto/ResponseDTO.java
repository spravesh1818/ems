package com.ems.ems.dto;

public class ResponseDTO {
    private String token;
    private String refreshToken;
    private String type;


    public ResponseDTO(String token, String refreshToken, String type) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.type = type;
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
