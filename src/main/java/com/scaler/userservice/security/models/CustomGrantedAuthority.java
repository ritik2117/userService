package com.scaler.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.models.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * As implemented CustomUserDetails (which is acting as a DTO of User Entity)
 * Similarly implemented GrantedAuthority to map the values
 * of the CustomGrantedAuthority (Returned by the Spring Authorization Server)
 * with the Role Entity.
 */
@Data
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
//    private Role role;
    private String authority;

    /**
     * Needed by Jackson to deserialize the object from JSON to
     * CustomGrantedAuthority object (DTO) of Role Entity
     */
    public CustomGrantedAuthority() {
    }

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
