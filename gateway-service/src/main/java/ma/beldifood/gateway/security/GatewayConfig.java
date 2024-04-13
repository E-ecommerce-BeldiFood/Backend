package ma.beldifood.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("User-management-microservice", r -> r.path("/auth/authenticate")
                        .uri("lb://Authentication-microservice"))

                .route("Authentication-microservice", r -> r.path("/auth/users")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Authentication-microservice"))
                .build();
    }

}