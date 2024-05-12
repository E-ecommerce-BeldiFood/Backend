package ma.beldifood.securityservice.restClient;

<<<<<<< HEAD
import jakarta.mail.MessagingException;
import ma.beldifood.securityservice.model.dto.ResetPasswordDTO;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import ma.beldifood.securityservice.model.dto.UserDto;
import ma.beldifood.securityservice.model.dto.UserRegisterRequest;
import ma.beldifood.securityservice.model.dto.UserRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @PostMapping("/users/register")
    ResponseEntity<UserRegisterResponse> save(@RequestBody UserRegisterRequest request);
<<<<<<< HEAD
    @GetMapping("/users/by-login")
    ResponseEntity<UserDto> getUserByLogin(@RequestParam("login") String login);


    @GetMapping("/users/confirm-account")
    ResponseEntity<Boolean> confirmUserAccount(@RequestParam("token") String confirmationToken);

    @PostMapping("/users/send-reset-password")
    ResponseEntity<String> handleSendResetPassword(@RequestParam("email") String email) throws MessagingException;

    @PostMapping("/users/reset-password")
    ResponseEntity<String> handleRestPassword( @RequestBody ResetPasswordDTO changePasswordDTO );

=======


    @GetMapping("/users/by-login")
    ResponseEntity<UserDto> getUserByLogin(@RequestParam("login") String login);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}