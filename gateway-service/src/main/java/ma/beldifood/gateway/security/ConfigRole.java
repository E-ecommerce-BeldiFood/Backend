package ma.beldifood.gateway.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Component
@AllArgsConstructor
public class ConfigRole {
    private final JwtUtil jwtUtils;

    public boolean filtersRoles(String token, String path) {
        log.info("ùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùù");
        Claims claims = jwtUtils.getAllClaimsFromToken(token);
        //plm
        String iss = claims.get("iss", String.class);
        Pattern pattern = Pattern.compile("role=(\\w+)");
        Matcher matcher = pattern.matcher(iss);
        List<String> roles = new ArrayList<>();
        while (matcher.find()) {
            String role = matcher.group(1);
            roles.add(role);
        }
        if (roles.isEmpty()) return false;
        if (roles.contains("ADMIN")) return true;
        if (roles.contains("CLIENT") && !roles.contains("ADMIN")) {
            if (path.contains("/admin")) {
                return false;
            }
            return true;
        }
        return false;
}

}
