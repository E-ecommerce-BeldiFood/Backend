package ma.beldifood.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterResponse {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phone;

}
