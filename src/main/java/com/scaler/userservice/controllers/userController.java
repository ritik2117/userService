package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LogOutRequestDto;
import com.scaler.userservice.dtos.LoginRequestDto;
import com.scaler.userservice.dtos.SignUpRequestDto;
import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
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
        return null;
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto requestDto) {
        return null;
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token) {
        return null;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return null;
    }
}
