package ma.beldifood.securityservice.service;

import lombok.RequiredArgsConstructor;
import ma.beldifood.securityservice.model.MyUserDetails;
import ma.beldifood.securityservice.restClient.UserServiceClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserServiceClient userServiceClient;
    @Override
<<<<<<< HEAD
    //exceptions
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = userServiceClient.getUserByLogin(login).getBody();
        assert user != null;
        return new MyUserDetails(user);

<<<<<<< HEAD


=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    }






}
