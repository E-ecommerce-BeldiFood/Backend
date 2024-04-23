package ma.beldifood.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginRequest {
    private String login;
    private String password;

}
