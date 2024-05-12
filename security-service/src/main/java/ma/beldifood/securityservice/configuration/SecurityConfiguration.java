package ma.beldifood.securityservice.configuration;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import ma.beldifood.securityservice.service.JpaUserDetailsService;
=======
import ma.beldifood.securityservice.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
<<<<<<< HEAD
@AllArgsConstructor
public class SecurityConfiguration  {


=======
public class SecurityConfiguration  {

    @Autowired
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    private JpaUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> {
                    csrf.disable();
                })
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> {
<<<<<<< HEAD
                   // auth.requestMatchers("/users/**").hasAnyRole("ADMIN");
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
                    auth.requestMatchers("/error/**").permitAll();
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                }).build();

    }

    @Bean
    public AuthenticationManager authManager() {

        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
