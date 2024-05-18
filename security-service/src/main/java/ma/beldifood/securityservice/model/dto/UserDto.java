package ma.beldifood.securityservice.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.beldifood.securityservice.model.Status;

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
    private Status status;
}
