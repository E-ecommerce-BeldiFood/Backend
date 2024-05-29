package ma.beldifood.gateway.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RefreshScope
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {

    private final ConfigRole configRole;
    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Value("${jwt.prefix}")
    public String TOKEN_PREFIX;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            if (authMissing(request)) return onError(exchange);
            String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            System.out.println("headers size:" + request.getHeaders().getOrEmpty("Authorization").size());
            System.out.println("raw token: " + token);
            if (token != null && token.startsWith("Bearer ")) token = token.substring(7);
            try {
                String path=exchange.getRequest().getURI().getPath();
                if (!configRole.filtersRoles(token, path)) return onError(null);
                // we use this method to validate token

                    Claims claims = jwtUtil.getAllClaimsFromToken(token);
                    System.out.println("claim: " + claims.getIssuer());

                System.out.println("token after processing : "+ token);
            } catch (Exception e) {
                e.printStackTrace();
                return onError(exchange);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    //check if the authorization header excite in the request
    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
