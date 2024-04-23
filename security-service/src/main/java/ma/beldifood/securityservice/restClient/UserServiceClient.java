package ma.beldifood.securityservice.restClient;

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


    @GetMapping("/users/by-login")
    ResponseEntity<UserDto> getUserByLogin(@RequestParam("login") String login);
}