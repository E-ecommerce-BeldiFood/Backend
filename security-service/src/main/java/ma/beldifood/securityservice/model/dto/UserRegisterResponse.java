package ma.beldifood.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.beldifood.securityservice.model.Status;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterResponse {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phone;
    private LocalDateTime createdAt;
    private Status status;
    private LocalDateTime updatedAt;
    private Date confirmedAt;

}
