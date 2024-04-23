package ma.beldifood.securityservice.service;

import lombok.AllArgsConstructor;
import ma.beldifood.securityservice.Exception.WrongCredentialsException;
import ma.beldifood.securityservice.configuration.JwtHelpers;
import ma.beldifood.securityservice.model.dto.TokenDto;
import ma.beldifood.securityservice.model.dto.UserLoginRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterResponse;
import ma.beldifood.securityservice.restClient.UserServiceClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtHelpers jwtHelper;


    public TokenDto login(UserLoginRequest request) {
        Authentication credentials = new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword());
        Authentication principal = authenticationManager.authenticate(credentials);
        if (principal.isAuthenticated())
        {
            SecurityContextHolder.getContext().setAuthentication(principal);
            return  TokenDto
                    .builder()
                    .token(jwtHelper.generateToken(request.getLogin()))
                    .build();
        }
        else throw new WrongCredentialsException("Wrong credentials");
    }

    public UserRegisterResponse register(UserRegisterRequest request) {
        return userServiceClient.save(request).getBody();
    }
}
