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
                .route("security-service", r -> r.path("/auth/**")
                        .uri("lb://security-service"))

                .route("user-service", r -> r.path("/users/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://user-service"))

                .route("Product-catalog-service" , r -> r.path("/products/**")
                        .uri("lb://Product-catalog-service"))

                .route("Product-catalog-service" , r -> r.path("/categories/**")
                        .uri("lb://Product-catalog-service"))

                .route("Product-catalog-service" , r -> r.path("/subcategories/**")
                .uri("lb://Product-catalog-service"))

                .route("Orders" , r -> r.path("/orders/**")
                        .uri("lb://Orders"))

                .route("review-service" , r -> r.path("/reviews/**")
                        .uri("lb://review-service"))

                .build();

    }

}