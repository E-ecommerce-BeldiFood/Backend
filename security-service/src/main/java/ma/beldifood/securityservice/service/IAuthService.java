package ma.beldifood.securityservice.service;

import ma.beldifood.securityservice.model.dto.TokenDto;
import ma.beldifood.securityservice.model.dto.UserLoginRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterResponse;

public interface IAuthService {
    public TokenDto login(UserLoginRequest request);
    public UserRegisterResponse register(UserRegisterRequest request);
}
