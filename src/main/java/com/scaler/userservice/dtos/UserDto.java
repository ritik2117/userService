package com.scaler.userservice.dtos;

import com.scaler.userservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}
