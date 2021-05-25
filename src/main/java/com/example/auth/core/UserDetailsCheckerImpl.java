package com.example.auth.core;

import com.example.auth.db.jpa.entities.User;
import com.example.auth.enums.UserStatus;
import com.example.auth.core.exceptions.AppException;
import com.example.auth.core.rest.response.AppErrorCode;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailsCheckerImpl implements UserDetailsChecker {

    @Override
    public void check(User user) {
        // password expire
        if (user.getPasswordExpiredAt().isBefore(Instant.now())) {
            throw new AppException(AppErrorCode.PASSWORD_IS_EXPIRED);
        }

        // check status
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new AppException(AppErrorCode.USER_NOT_ACTIVE);
        }

        // check enable
        if (!user.isEnabled()) {
            throw new AppException(AppErrorCode.USER_NOT_ENABLED);
        }
    }
}
