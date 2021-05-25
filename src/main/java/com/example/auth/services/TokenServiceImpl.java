package com.example.auth.services;

import static com.example.auth.enums.TokenStatus.ACTIVE;

import com.example.auth.db.jpa.entities.SecurityToken;
import com.example.auth.db.jpa.entities.User;
import com.example.auth.db.jpa.repositories.SecurityTokenRepository;
import java.time.Instant;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final SecurityTokenRepository repository;

    private static final Long ACCESS_TOKEN_LIFE_TIME_IN_SECONDS = 10 * 60L; // 10 minutes
    private static final Long REFRESH_TOKEN_LIFE_TIME_IN_SECONDS = 30 * 60L; // 30 minutes

    private final HttpServletRequest request;

    // todo cuongbm: https://www.baeldung.com/spring-rest-http-headers
    @Override
    public SecurityToken createTokenForUser(User user) {
        request.getHeader("");
        Instant now = Instant.now();

        SecurityToken token = SecurityToken.builder()
            .user(user)
            .accessToken(UUID.randomUUID().toString())
            .refreshToken(UUID.randomUUID().toString())
            .status(ACTIVE)
            .issuedAt(now)
            .accessTokenExpiresAt(now.plusSeconds(ACCESS_TOKEN_LIFE_TIME_IN_SECONDS))
            .refreshTokenExpiresAt(now.plusSeconds(REFRESH_TOKEN_LIFE_TIME_IN_SECONDS))
            .lastSeenAt(now)
            .ipAddress(getIPFromHeader())
            .agentInformation(getAgentInfoFromHeader())
            .build();

        SecurityToken savedToken = repository.save(token);
        return savedToken;
    }

    private String getAgentInfoFromHeader() {
        return request.getHeader("User-Agent");
    }

    // fixme : header for get IP can be: X-FORWARDED-FOR, HTTP_CLIENT_IP ...
    // It should follow config nginx or load balancer
    private String getIPFromHeader() {
        return request.getHeader("host");
    }
}
