package com.example.securitymicroservice.request;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
}
