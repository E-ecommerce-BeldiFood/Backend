package com.example.securitymicroservice.dto;

import com.example.securitymicroservice.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}
