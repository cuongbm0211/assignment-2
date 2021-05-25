package com.example.auth.db.jpa.entities;

import com.example.auth.enums.TokenStatus;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "security_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class SecurityToken implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_status")
    @Enumerated(EnumType.STRING)
    private TokenStatus status;

    @Column(name = "issued_at")
    private Instant issuedAt;

    @Column(name = "access_token_expires_at")
    private Instant accessTokenExpiresAt;

    @Column(name = "refresh_token_expires_at")
    private Instant refreshTokenExpiresAt;

    // todo: will update when call api token_instrospect
    @Column(name = "last_seen_at")
    private Instant lastSeenAt;

    @Column(name = "ip_address")
    private String ipAddress;

    // todo cuongbm: will get from request
    @Column(name = "agent_information")
    private String agentInformation;

}

