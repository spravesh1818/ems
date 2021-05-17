package com.ems.ems.config;

import com.ems.ems.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse {
    private final String token;
    private final UserDto userDto;

    public AuthenticationResponse(String token,UserDto userDto) {
        this.token = token;
        this.userDto=userDto;
    }

    public String getToken() {
        return token;
    }

    public UserDto getUserDetails() {
        return userDto;
    }
}
