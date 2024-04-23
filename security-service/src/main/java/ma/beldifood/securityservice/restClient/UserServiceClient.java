package ma.beldifood.securityservice.restClient;

import ma.beldifood.securityservice.model.dto.UserDto;
import ma.beldifood.securityservice.model.dto.UserRegisterDto;
import ma.beldifood.securityservice.model.dto.UserRegisterRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-microservice")
public interface UserServiceClient {
    @PostMapping("/users/register")
    ResponseEntity<UserRegisterDto> save(@RequestBody UserRegisterRequest request);

    @GetMapping("/users/username={username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}