package ma.beldifood.User.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true, nullable = false, updatable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(unique = true, nullable = false)
    //Todo validation of phone number using patten validation
    private String phone;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Date confirmedAt;

    private String confirmationToken;
    private String resetPasswordToken;
    private Date resetPasswordTokenExpiryDate;

    //

}
