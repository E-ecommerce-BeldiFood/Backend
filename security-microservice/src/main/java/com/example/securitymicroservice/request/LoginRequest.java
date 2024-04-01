package com.example.securitymicroservice.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
