package com.example.auth.services;

import static com.example.auth.enums.TokenStatus.ACTIVE;
import static com.example.auth.services.TokenServiceImpl.ACCESS_TOKEN_LIFE_TIME_IN_SECONDS;
import static com.example.auth.services.TokenServiceImpl.REFRESH_TOKEN_LIFE_TIME_IN_SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.example.auth.db.jpa.entities.SecurityToken;
import com.example.auth.db.jpa.entities.User;
import com.example.auth.db.jpa.repositories.SecurityTokenRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @Mock
    SecurityTokenRepository repository;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    TokenServiceImpl tokenService;

    Clock fixedClock = Clock.fixed(
        Instant.parse("2021-05-26T10:00:00Z"),
        ZoneOffset.UTC);

    User user = User.builder()
        .build();

    @BeforeEach
    void setup() {
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0");
        when(request.getHeader("host")).thenReturn("127.0.0.1");

        tokenService = new TokenServiceImpl(repository, request);
        tokenService.setClock(fixedClock);
    }

    @Test
    void whenBuildToken_ThenReturnNewToken() {
        User user = User.builder()
            .build();

        SecurityToken securityToken = tokenService.buildSecurityToken(user);

        assertThat(securityToken.getUser(), sameInstance(user));
        assertThat(securityToken.getAccessToken(), notNullValue());
        assertThat(securityToken.getRefreshToken(), notNullValue());
        assertThat(securityToken.getStatus(), is(ACTIVE));

        Instant expireAfter10Minutes = fixedClock.instant().plusSeconds(ACCESS_TOKEN_LIFE_TIME_IN_SECONDS);
        assertThat(securityToken.getAccessTokenExpiresAt().equals(expireAfter10Minutes), is(true));

        Instant expiredAfter30Minutes = fixedClock.instant().plusSeconds(REFRESH_TOKEN_LIFE_TIME_IN_SECONDS);
        assertThat(securityToken.getRefreshTokenExpiresAt().equals(expiredAfter30Minutes), is(true));

        assertThat(securityToken.getLastSeenAt(), notNullValue());
        assertThat(securityToken.getIpAddress(), is("127.0.0.1"));
        assertThat(securityToken.getAgentInformation(), is("Mozilla/5.0"));
    }

    @Test
    void whenCreateToken_ThenPersisTokenDb() {
        when(repository.save(any())).thenReturn(any());

        tokenService.createTokenForUser(user);
        verify(repository, times(1)).save(any());
    }
}