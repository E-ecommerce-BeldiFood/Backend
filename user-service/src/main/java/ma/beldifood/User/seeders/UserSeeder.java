package ma.beldifood.User.seeders;

import ma.beldifood.User.entities.Role;
import ma.beldifood.User.entities.Status;
import ma.beldifood.User.entities.User;
import ma.beldifood.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserSeeder {

    @Value("${user.username}")
    private String userName;

    @Value("${user.firstName}")
    private String firstName;

    @Value("${user.lastName}")
    private String lastName;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Value("${user.phone}")
    private String phone;

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        User user = User.builder()
                .id(Long.valueOf(1))
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .role(Role.ADMIN)
                .active(true)
                .status(Status.ENABLED)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .build();
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {userRepository.save(user);}
        };

    }

}
