package com.authservice.detials;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthResponse {
    @Getter
    private String token;
    @Getter
    private String username;
    @Getter
    private Collection<? extends GrantedAuthority> roles;
    public AuthResponse(String token) {
        this.token = token;
    }

//    public AuthResponse(String token, String username, List<String> roles) {
//        this.token = token;
//        this.username = username;
//        this.roles = roles;
//    }

    public AuthResponse(String jwt, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = jwt;
        this.username = username;
        this.roles = authorities;
    }
}