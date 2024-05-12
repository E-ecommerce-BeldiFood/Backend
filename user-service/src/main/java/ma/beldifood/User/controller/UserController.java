package ma.beldifood.User.controller;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.beldifood.User.dto.ResetPasswordDTO;
import ma.beldifood.User.dto.UserRequestDto;
import ma.beldifood.User.dto.UserResponseDto;
import ma.beldifood.User.entities.User;
import ma.beldifood.User.exception.EmailAlreadyExistsException;
import ma.beldifood.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto) throws MessagingException {
        UserResponseDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/username/{userName}")
    //@PreAuthorize("hasRole('ADMIN')")
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
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/admin")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userDto) {
        UserResponseDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/by-login")
    public ResponseEntity<User> getUserByLogin(@RequestParam String login){
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto)throws EmailAlreadyExistsException, MessagingException {
     return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Boolean>confirmUserAccount(@RequestParam("token") String confirmationToken) {
         return ResponseEntity.ok(userService.confirmEmail(confirmationToken));
    }

    @PostMapping("/send-reset-password")
    public ResponseEntity<String> handleResetPassword(@RequestParam("email") String email) throws MessagingException {
        userService.sendResetPasswordMail(email);
        return ResponseEntity.ok("Un email de réinitialisation a été envoyé à " + email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> handleChangePassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok("Le mot de passe a été changé avec succès");
    }


}
