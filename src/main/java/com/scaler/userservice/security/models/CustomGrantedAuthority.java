package com.scaler.userservice.security.models;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "";
    }
}
