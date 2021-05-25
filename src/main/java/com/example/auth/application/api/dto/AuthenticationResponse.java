package com.example.auth.application.api.dto;

import com.example.auth.db.jpa.entities.SecurityToken;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    String accessToken;
    String refresh_token;
    Instant accessTokenExpiresAt;
    Instant refreshTokenExpiresAt;

    public AuthenticationResponse(SecurityToken authenticate) {
        this.accessToken = authenticate.getAccessToken();
        this.refresh_token = authenticate.getRefreshToken();
        this.accessTokenExpiresAt = authenticate.getAccessTokenExpiresAt();
        this.refreshTokenExpiresAt = authenticate.getRefreshTokenExpiresAt();
    }
}
