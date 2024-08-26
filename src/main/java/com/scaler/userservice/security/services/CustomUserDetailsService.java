package com.scaler.userservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import com.scaler.userservice.security.models.CustomUserDetails;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }
}
