package ma.beldifood.securityservice.controller;

<<<<<<< HEAD
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import ma.beldifood.securityservice.Exception.WrongCredentialsException;
import ma.beldifood.securityservice.model.dto.*;
import ma.beldifood.securityservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
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
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

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
<<<<<<< HEAD


    @GetMapping("/confirm-account")
    public ResponseEntity<Boolean> validationEmail(@RequestParam("token") String confirmationToken) {
        return ResponseEntity.ok(authService.confirmEmail(confirmationToken));
    }

    @PostMapping("/send-reset-password")
    public ResponseEntity<String> handleResetPassword(@RequestParam("email") String email) throws MessagingException {
        return ResponseEntity.ok(authService.handleSendResetPassword(email));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> handleChangePassword(@RequestParam("token") String token, @RequestBody ResetPasswordDTO resetPasswordDTO) {
        resetPasswordDTO.setToken(token);
        authService.handleSendPassword(resetPasswordDTO);
        return ResponseEntity.ok("Le mot de passe a été changé avec succès");
    }
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
