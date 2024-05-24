package ma.beldifood.securityservice.service;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import ma.beldifood.securityservice.Exception.WrongCredentialsException;
import ma.beldifood.securityservice.configuration.JwtHelpers;
import ma.beldifood.securityservice.model.dto.*;
import ma.beldifood.securityservice.restClient.UserServiceClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@AllArgsConstructor
@Service
@CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtHelpers jwtHelper;


    public TokenDto login(UserLoginRequest request) {
        Authentication credentials = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
        Authentication principal = authenticationManager.authenticate(credentials);
            if (principal.isAuthenticated() ) {
                SecurityContextHolder.getContext().setAuthentication(principal);
                UserDto user = userServiceClient.getUserByLogin(request.getLogin()).getBody();
                assert user != null;
                return TokenDto.builder()
                        .accessToken(jwtHelper.generateToken(user,JwtHelpers.ACCESS_TOKEN_EXPIRATION_MS))
                        .refreshToken(jwtHelper.generateToken(user,JwtHelpers.REFRESH_TOKEN_EXPIRATION_MS))
                        .login(request.getLogin())
                        .role(user.getRole())
                        .user(user)
                        .build();
            } else {
                throw new WrongCredentialsException(" Invalid credentials");
            }
    }

    public UserRegisterResponse register(UserRegisterRequest request) {
        UserRegisterResponse  userRegisterResponse = userServiceClient.save(request).getBody();
        System.out.println("its work");
        return userRegisterResponse;
    }




    public Boolean confirmEmail(String confirmationToken) {
        return userServiceClient.confirmUserAccount(confirmationToken).getBody();

    }

    public String handleSendResetPassword(String email) throws MessagingException {
            return userServiceClient.handleSendResetPassword(email).getBody();

    }

    public void handleSendPassword(ResetPasswordDTO resetPasswordDTO) {
             userServiceClient.handleRestPassword(resetPasswordDTO).getBody();
    }
}
