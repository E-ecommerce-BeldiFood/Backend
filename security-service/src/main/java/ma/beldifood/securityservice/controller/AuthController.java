package ma.beldifood.securityservice.controller;

import lombok.RequiredArgsConstructor;
import ma.beldifood.securityservice.Exception.WrongCredentialsException;
import ma.beldifood.securityservice.model.dto.TokenDto;
import ma.beldifood.securityservice.model.dto.UserLoginRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterResponse;
import ma.beldifood.securityservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequest request) {
        try {
            TokenDto tokenDto = authService.login(request);
            return ResponseEntity.ok(tokenDto);
        } catch (WrongCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}
