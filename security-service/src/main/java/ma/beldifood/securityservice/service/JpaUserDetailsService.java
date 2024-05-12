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
    //exceptions
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = userServiceClient.getUserByLogin(login).getBody();
        assert user != null;
        return new MyUserDetails(user);



    }






}
