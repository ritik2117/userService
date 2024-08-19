package com.scaler.userservice.security.models;

import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This CustomUserDetails class will act like a User class for Spring Security.
 * As implemented CustomUserDetails (which is acting as a DTO of User Entity)
 * UserDetails is returned by the Spring Authorization Server
 * Not recommended to change the original models to implement any external frameworks
 * so created CustomUserDetails class (Returned by Spring Authorization Server)
 * to map the values to User entity
 */
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getHashedPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;

//        In the granted authorities, we need to add the roles.
        this.authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            /*CustomGrantedAuthority customGrantedAuthority = new CustomGrantedAuthority();
            customGrantedAuthority.setRole(role);
            authorities.add(customGrantedAuthority);*/
            authorities.add(new CustomGrantedAuthority(role));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return enabled;
    }
}
