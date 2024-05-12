package ma.beldifood.securityservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

<<<<<<< HEAD
    private String accessToken;
    private String login;
    private String refreshToken;
    private String role;
=======
    private String token;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
