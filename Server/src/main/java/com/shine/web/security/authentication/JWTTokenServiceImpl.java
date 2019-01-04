package com.shine.web.security.authentication;

import com.shine.common.config.ShineConfigReader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("JWTTokenServiceImpl")
public class JWTTokenServiceImpl implements JWTTokenService {


    @Override
    public String generateAuthenticationToken(String userName, List<String> roles) {
        String commaSeparatedRoles = String.join(",", roles);
        Claims claims = Jwts.claims()
                .setSubject("user_name")
                .setIssuedAt(new Date())
                .setIssuer("Shine")
                .setExpiration(getExpirationDate());

        claims.put("roles", commaSeparatedRoles);

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();

    }


    protected Date getExpirationDate() {
        final long jwtValidationPeriodMinute = ShineConfigReader.readLongProperty("jwt.token.validation.period.minute", 30);
        final long now = new Date().getTime();

        return new Date(now + Duration.ofMinutes(jwtValidationPeriodMinute).toMillis());
    }


    protected String getSecretKey() {
       return ShineConfigReader.readProperty("jwt.token.secret.key");
    }
}
