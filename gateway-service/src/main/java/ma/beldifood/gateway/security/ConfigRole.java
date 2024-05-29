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
        if (iss==null) return false;
        if (iss.contains("ADMIN")) return true;
        if (iss.contains("USER") ) {
            if (path.toLowerCase().contains("/admin")) {
                return false;
            }
            return true;
        }
        return false;
   }

}
