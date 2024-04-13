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
    private Long id;
    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private Role role;
}
