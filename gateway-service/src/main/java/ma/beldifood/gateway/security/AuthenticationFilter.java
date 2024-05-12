package ma.beldifood.gateway.security;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
import io.jsonwebtoken.Claims;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import org.springframework.beans.factory.annotation.Autowired;
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
<<<<<<< HEAD
@Slf4j
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private ConfigRole configRole;
=======

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.prefix}")
    public String TOKEN_PREFIX;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
<<<<<<< HEAD
        if (routerValidator.isSecured.test(request)) {
            if (authMissing(request)) return onError(exchange);
            String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            if (token != null && token.startsWith("Bearer ")) token = token.substring(7);
            try {
                // we use this method to validate token
                jwtUtil.getAllClaimsFromToken(token);
            } catch (Exception e) {
                return onError(exchange);
            }
=======
        System.out.println("filter");
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request) || this.isPrefixMissing(request)) {
                System.out.println("Authorization header is missing in request");
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            }
            final String token = this.getAuthHeader(request);

            if (jwtUtil.isInvalid(token)) {
                System.out.println("Authorization header is invalid");
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            }
            this.populateRequestWithHeaders(exchange, token);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
        }
        return chain.filter(exchange);
    }

<<<<<<< HEAD
    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    //check if the authorization header excite in the request
    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
=======

    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        var header = request.getHeaders().getOrEmpty("Authorization").get(0);
        return header.replace(TOKEN_PREFIX,"").trim();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private boolean isPrefixMissing(ServerHttpRequest request) {
        var header = request.getHeaders().getFirst ("Authorization");
        assert header != null;
        return !header.startsWith(TOKEN_PREFIX);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("roles", String.valueOf(claims.get("roles")))
                .header("tenantId", String.valueOf(claims.get("tenantId")))
                .build();
    }
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
