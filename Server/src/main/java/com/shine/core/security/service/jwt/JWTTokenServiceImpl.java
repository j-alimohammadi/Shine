package com.shine.core.security.service.jwt;

import com.shine.common.config.ShineConfigReader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTTokenServiceImpl implements JWTTokenService {
    private final static Logger log = LoggerFactory.getLogger(JWTTokenServiceImpl.class);


    @Override
    public JWTInfo generateAuthenticationToken(final String userName, final String sessionId) {
        final Date expirationDate = getExpirationDate();
        Claims claims = Jwts.claims()
                .setSubject("user_name")
                .setIssuedAt(new Date())
                .setIssuer("Shine")
                .setExpiration(expirationDate);

        claims.put("userName", userName);
        claims.put("sessionId", sessionId);

        final String tokenValue = Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();

        return new JWTInfo(tokenValue, sessionId, userName, expirationDate);
    }

    @Override
    public JWTInfo parseToken(final String jwsToken) {

        try {
            Claims claims = Jwts.parser().
                    setSigningKey(getSecretKey())
                    .parseClaimsJws(jwsToken)
                    .getBody();

            String userName = claims.get("userName", String.class);
            String sessionId = claims.get("sessionId", String.class);
            Date expiration = claims.getExpiration();

            return new JWTInfo(jwsToken, sessionId, userName, expiration);

        } catch (Exception ex) {
            throw new RuntimeException("Error on creating claim", ex);
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
