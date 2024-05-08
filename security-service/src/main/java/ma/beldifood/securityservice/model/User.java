package ma.beldifood.securityservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private boolean active=false;
    private String phone;

}
