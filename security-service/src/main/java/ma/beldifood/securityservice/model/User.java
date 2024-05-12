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
<<<<<<< HEAD
    private boolean active=false;
    private String phone;

=======
    private boolean active;
    private String phone;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
