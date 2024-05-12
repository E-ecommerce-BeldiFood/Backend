package com.example.User.controller;

import com.example.User.dto.UserRequestDto;
import com.example.User.dto.UserResponseDto;
import com.example.User.entities.User;
import com.example.User.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto) {
        UserResponseDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<UserResponseDto> getUserByUserName(@PathVariable String userName) {
        try {
            UserResponseDto user = userService.getUserByUserName(userName);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        try {
            UserResponseDto user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userDto) {
        UserResponseDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/by-login")
    public ResponseEntity<User> getUserByLogin(@RequestParam String login){
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
     return ResponseEntity.ok(userService.createUser(userRequestDto));
    }



}
