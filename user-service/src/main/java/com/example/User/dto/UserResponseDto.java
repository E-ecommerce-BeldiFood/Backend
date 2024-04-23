package com.example.User.dto;

import com.example.User.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String phone;
}
