package com.scaler.userservice.services;

import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private User signup(String name, String email, String password) {
        return null;
    }

    private Token login(String email, String password) {
        return null;
    }

    private void logout(String tokenValue) {

    }

    private User validateToken(String tokenValue) {
        return null;
    }
}
