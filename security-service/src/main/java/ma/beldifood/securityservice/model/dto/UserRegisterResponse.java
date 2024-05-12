package ma.beldifood.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.Date;

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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
<<<<<<< HEAD
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Date confirmedAt;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

}
