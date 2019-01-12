package com.shine.web.security.service;

import com.shine.common.config.ShineConfigReader;
import com.shine.web.security.domian.WebUser;
import io.jsonwebtoken.*;
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

    @Override
    public WebUser parseToken(String jwsToken) {

        try {
            Claims claims = Jwts.parser().
                    setSigningKey(getSecretKey())
                    .parseClaimsJws(jwsToken)
                    .getBody();

            WebUser webUser =


        } catch (ExpiredJwtException | UnsupportedJwtException
                | MalformedJwtException | SignatureException |
                IllegalArgumentException ex) {

        }

        return null;
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
