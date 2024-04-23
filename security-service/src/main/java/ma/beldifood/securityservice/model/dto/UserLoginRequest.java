package ma.beldifood.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginDto {
    private String userName;
    private String password;
    private String email;
    private String phone;
}
