package ma.beldifood.User.dto;

import lombok.*;
import ma.beldifood.User.entities.Role;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserRequestDto {

    private  Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Role role;
    private boolean active = true;
    private String phone;
    private String address;
    private String confirmationToken;
    private String resetPasswordToken;
}
