package com.shine.core.security.service.jwt;

import com.shine.common.config.ShineConfigReader;
import com.shine.core.security.dto.RegisteredUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTTokenServiceImpl implements JWTTokenService {
    private final static Logger log = LoggerFactory.getLogger(JWTTokenServiceImpl.class);


    @Override
    public JWTInfo generateAuthenticationToken(String userName, String sessionId) {
        final Date expirationDate = getExpirationDate();
        Claims claims = Jwts.claims()
                .setSubject("user_name")
                .setIssuedAt(new Date())
                .setIssuer("Shine")
                .setExpiration(getExpirationDate());

        claims.put("userName", userName);
        claims.put("sessionId", sessionId);

        final String tokenValue = Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();

        return new JWTInfo(tokenValue, expirationDate);
    }

    @Override
    public Optional<RegisteredUser> parseToken(String jwsToken) {

        try {
            Claims claims = Jwts.parser().
                    setSigningKey(getSecretKey())
                    .parseClaimsJws(jwsToken)
                    .getBody();

            String userName = claims.get("userName", String.class);
            List<String> roles = Arrays.asList(claims.get("roles", String.class).split(","));

            RegisteredUser registeredUser = new RegisteredUser(userName, roles);
            return Optional.of(registeredUser);

        } catch (Exception ex) {
            log.error("Error on creating claim", ex);
            return Optional.empty();
        }

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
