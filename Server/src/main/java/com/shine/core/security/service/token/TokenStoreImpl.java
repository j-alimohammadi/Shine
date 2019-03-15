package com.shine.core.security.service.token;

import com.shine.core.security.utils.TokenMasker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("tokenStoreImpl")
public class TokenStoreImpl implements TokenStore {
    private final static Logger log = LoggerFactory.getLogger(TokenStoreImpl.class);

    private ConcurrentHashMap<String, UUID> tokensToSessionId = new ConcurrentHashMap<>();
    private DelayQueue<TokenExpiry> expireToken = new DelayQueue<>();

    private class TokenExpiry implements Delayed {
        private final long expireTimeNano;
        private final String tokenValue;

        public TokenExpiry(String tokenValue, long expireTimeNano) {
            this.expireTimeNano = expireTimeNano;
            this.tokenValue = tokenValue;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            final long diff = expireTimeNano - System.nanoTime();
            return unit.convert(diff, TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            long diff = getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);

            if (diff == 0) {
                return 0;
            } else if (diff > 0) {
                return 1;
            } else {
                return -1;
            }

        }

    }


    @Override
    public UUID getSessionId(final String token) {
        return tokensToSessionId.get(token);
    }

    @Override
    public void storeToken(final String token, final UUID sessionId) {
        tokensToSessionId.put(token, sessionId);
    }

    @Override
    public void removeToken(final String token) {
        tokensToSessionId.remove(token);
        log.info("Token [{}] removed", TokenMasker.maskToken(token));
    }

    @Override
    public void deleteExpiredToken() {
        TokenExpiry polledToken = expireToken.poll();

        while (!Objects.isNull(polledToken)) {
            log.info("Expiring token [{}]", TokenMasker.maskToken(polledToken.tokenValue));
            removeToken(polledToken.tokenValue);

            polledToken = expireToken.poll();
        }


    }
}
