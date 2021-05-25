package com.example.auth.services;

import com.example.auth.core.PasswordEncoder;
import com.example.auth.core.UserDetailsChecker;
import com.example.auth.db.jpa.entities.SecurityToken;
import com.example.auth.db.jpa.entities.User;
import com.example.auth.event.AuthenticationFailureEvent;
import com.example.auth.event.handler.AuthenticationFailureHandler;
import com.example.auth.event.AuthenticationSuccessEvent;
import com.example.auth.event.handler.AuthenticationSuccessHandler;
import com.example.auth.core.exceptions.AppException;
import com.example.auth.core.rest.response.AppErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsChecker userDetailsChecker;

    private final TokenService tokenService;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public SecurityToken authenticate(String username, String password) {
        try {
            Assert.hasText(username, "Username cannot empty");
            Assert.hasText(password, "Password cannot empty");

            User user = retrieveUser(username);
            userDetailsChecker.check(user);

            if (!passwordEncoder.matches(password, user.getPasswordHash())){
                throw new AppException(AppErrorCode.BAD_CREDENTIAL);
            }

            authenticationSuccessHandler.handle(AuthenticationSuccessEvent.builder()
                                                    .username(username)
                                                    .build());

            return tokenService.createTokenForUser(user);

        } catch (Exception e) {
            authenticationFailureHandler.handle(AuthenticationFailureEvent.builder()
                                                                    .username(username)
                                                                    .build());
            throw e;
        }
    }

    private User retrieveUser(String username) {
        return userService.loadUserDetailsByUserName(username)
                          .orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
    }
}
