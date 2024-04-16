package com.example.User.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

}
