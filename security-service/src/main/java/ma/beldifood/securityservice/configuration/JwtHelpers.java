package ma.beldifood.securityservice.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
<<<<<<< HEAD
import ma.beldifood.securityservice.model.dto.UserDto;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtHelpers {


    @Value("${spring.security.key}")
    private String SECRET_KEY;
<<<<<<< HEAD
    public static final long REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7;
    public static final long ACCESS_TOKEN_EXPIRATION_MS = 1000 * 60 * 60;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

<<<<<<< HEAD
    public String generateToken(UserDto user, Long expirationTime) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user,expirationTime);
    }

    private String createToken(Map<String, Object> claims, UserDto user,Long expirationTime) {
        Date now = new Date();
        Date tokenExpiryDate = new Date(now.getTime() +expirationTime);
        return Jwts.builder().setClaims(claims).setSubject(user.getUserName()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(tokenExpiryDate)
                .setIssuer(user.getRole())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();


=======
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
