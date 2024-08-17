package com.scaler.userservice.services;

import com.scaler.userservice.exceptions.UserNotFoundException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public User signup(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setEmailVerified(true);
//        save the user object to the DB.
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
//        Check if the user exists or not
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
//            TODO: Throw some exception.
            return null;
        }
//        Login successful, generate a Token.
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    public Token generateToken(User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysFromCurrentDate = currentDate.plusDays(30);
        ZonedDateTime zonedExpiryDateTime = thirtyDaysFromCurrentDate.atStartOfDay(ZoneId.systemDefault());
        Date expiryDate = Date.from(zonedExpiryDateTime.toInstant());
//        128 character alphanumeric string.
        String value = RandomStringUtils.randomAlphabetic(128);
        Token token = new Token();
        token.setUser(user);
        token.setValue(value);
        token.setExpiryDate(expiryDate);
        return token;
    }

    public void logout(String tokenValue) {

    }

    public User validateToken(String tokenValue) {
        return null;
    }
}
