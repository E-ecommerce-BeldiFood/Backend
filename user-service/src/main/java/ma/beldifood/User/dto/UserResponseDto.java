package ma.beldifood.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.beldifood.User.entities.Role;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Date confirmedAt;
}
