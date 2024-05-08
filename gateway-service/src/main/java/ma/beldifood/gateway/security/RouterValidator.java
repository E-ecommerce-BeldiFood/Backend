package ma.beldifood.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest ;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/reset-password"
    );
// predicate func that takes request and need to return always boolean
    public Predicate<ServerHttpRequest> isSecured =
        //takes request and return boolen ,check if the httprequest uri match the  openApiEndpoints paths
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}