package ma.beldifood.securityservice.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private String role;
    private boolean active;
    private String phone;
}
