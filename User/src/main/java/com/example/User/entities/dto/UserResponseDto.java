package com.example.User.entities.dto;

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
    private String userName;
    private String email;
    private Role role;
}
