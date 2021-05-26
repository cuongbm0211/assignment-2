package com.example.auth.core;

import static org.assertj.core.api.Assertions.*;

import com.example.auth.core.exceptions.AppException;
import com.example.auth.db.jpa.entities.User;
import com.example.auth.enums.UserStatus;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class UserDetailsCheckerImplTest {

    UserDetailsChecker userChecker = new UserDetailsCheckerImpl();

    @Test
    void givenUserExpiredPassword_ThenThrowPasswordIsExpired() {
        User userNotEnable = User.builder()
            .passwordExpiredAt(Instant.MIN)
            .status(UserStatus.LOCK)
            .enabled(true)
            .build();

        assertThatThrownBy(() -> userChecker.check(userNotEnable))
            .isInstanceOf(AppException.class)
            .extracting(ex -> ((AppException) ex).getCode())
            .isEqualTo("000101");
    }

    @Test
    void givenUserNotActive_ThenThrowUserNotActive() {
        User userExpired = User.builder()
            .passwordExpiredAt(Instant.MAX)
            .status(UserStatus.EXPIRED)
            .enabled(true)
            .build();

        assertThatThrownBy(() -> userChecker.check(userExpired))
            .isInstanceOf(AppException.class)
            .extracting(ex -> ((AppException) ex).getCode())
            .isEqualTo("000102");

        User userLock = User.builder()
            .passwordExpiredAt(Instant.MAX)
            .status(UserStatus.LOCK)
            .enabled(true)
            .build();

        assertThatThrownBy(() -> userChecker.check(userLock))
            .isInstanceOf(AppException.class)
            .extracting(ex -> ((AppException) ex).getCode())
            .isEqualTo("000102");
    }

    @Test
    void givenUserNotEnable_ThenThrowUserNotEnable() {
        User userNotEnabled = User.builder()
            .passwordExpiredAt(Instant.MAX)
            .status(UserStatus.ACTIVE)
            .enabled(false)
            .build();

        assertThatThrownBy(() -> userChecker.check(userNotEnabled))
            .isInstanceOf(AppException.class)
            .extracting(ex -> ((AppException) ex).getCode())
            .isEqualTo("000103");
    }

    @Test
    void givenUserSatisfyUser_ThenSuccess() {
        User userNotEnable = User.builder()
            .passwordExpiredAt(Instant.MAX)
            .status(UserStatus.ACTIVE)
            .enabled(true)
            .build();

        userChecker.check(userNotEnable);
    }
}