package com.example.auth.db.jpa.entities;

import com.example.auth.enums.UserStatus;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    Set<Group> groups;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "authentication_config_id")
    private String authenticationConfigId;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "password_updated_at")
    private Instant passwordUpdatedAt;

    @Column(name = "password_expired_at")
    private Instant passwordExpiredAt;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "enabled")
    private boolean enabled;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createAt;

    @Column(name = "created_by")
    private String createBy;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;
}
