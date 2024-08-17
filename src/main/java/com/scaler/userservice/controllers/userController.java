package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LogOutRequestDto;
import com.scaler.userservice.dtos.LoginRequestDto;
import com.scaler.userservice.dtos.SignUpRequestDto;
import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class userController {

    private UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = new User();
        if (requestDto != null) {
            user = userService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        }
        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) {
        Token token = new Token();
        if (requestDto != null) {
            token = userService.login(requestDto.getEmail(), requestDto.getPassword());
        }
        return token;
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto requestDto) {
//        TODO: Put it in try catch block when raised the exception from logout service
        if (requestDto != null) {
            userService.logout(requestDto.getTokenValue());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDto validateToken(@PathVariable String tokenValue) {
        User user = userService.validateToken(tokenValue);
        return UserDto.from(user);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return null;
    }
}
