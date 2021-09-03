package va.com.szkal.discordimageviewer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    private final String jwtSecret;

    public static final int TOKEN_EXPIRATION = 30 * 60;

    public JwtService(Environment environment) {
        jwtSecret = environment.getProperty("DIV_JWT_SECRET");
        if (jwtSecret == null) {
            throw new RuntimeException("DIV_JWT_SECRET env not present");
        }
    }

    public String getJwt(String server) {
        var expiredAtLDT = LocalDateTime.now().plusSeconds(TOKEN_EXPIRATION);
        return Jwts.builder()
                .setSubject(server)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expiredAtLDT.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean isTokenValid(String authToken) {
        if (authToken == null || authToken.equals("")) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException ignored) {
            return false;
        }
    }

    public String getServerFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
