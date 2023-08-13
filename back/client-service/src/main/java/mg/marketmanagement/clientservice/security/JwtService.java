package mg.marketmanagement.clientservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    public static final String SECRET_KEY= "yAVWUsHOZeqO2mF2ryaJxO3Ggfw02dB1nRbsNSSwwoYmI6OugTtg1c/rJUOGfdbY\n";

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isValidToken(String jwt, UserDetails userDetails){
        final String username= extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }
    public boolean isTokenExpired(String jwt){
        return extractClaims(jwt, Claims::getExpiration).before(new Date());
    }
    public String extractUsername(String jwt){
        return extractClaims(jwt, Claims::getSubject);
    }
    public <T> T extractClaims(String jwt, Function<Claims, T> claimsSolver){
        final Claims claims= extractAllClaims(jwt);
        return claimsSolver.apply(claims);
    }
    public Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
