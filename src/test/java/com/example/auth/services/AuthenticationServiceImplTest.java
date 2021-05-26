package com.example.auth.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.auth.core.PasswordEncoder;
import com.example.auth.core.UserDetailsChecker;
import com.example.auth.core.exceptions.AppException;
import com.example.auth.db.jpa.entities.User;
import com.example.auth.event.handler.AuthenticationFailureHandler;
import com.example.auth.event.handler.AuthenticationSuccessHandler;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserDetailsChecker userDetailsChecker;

    @Mock
    TokenService tokenService;

    @Mock
    AuthenticationFailureHandler authenticationFailureHandler;

    @Mock
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setup() {
        this.authenticationService = new AuthenticationServiceImpl(
            userService,
            passwordEncoder,
            userDetailsChecker,
            tokenService,
            authenticationFailureHandler,
            authenticationSuccessHandler
        );
    }

    @Test
    void givenEmptyUsername_ThenAuthenticationFailureHandlerExecute() {
        doNothing().when(authenticationFailureHandler).handle(any());

        String emptyUsername = "";
        String validPassword = "password";

        assertThatThrownBy(() -> authenticationService.authenticate(emptyUsername, validPassword))
            .isInstanceOf(IllegalArgumentException.class);

        verify(authenticationFailureHandler, times(1)).handle(any());
    }

    @Test
    void givenEmptyPassword_ThenAuthenticationFailureHandlerExecute() {
        doNothing().when(authenticationFailureHandler).handle(any());

        String validUsername = "alex@test.com";
        String emptyPassword = "";

        assertThatThrownBy(() -> authenticationService.authenticate(validUsername, emptyPassword))
            .isInstanceOf(IllegalArgumentException.class);

        verify(authenticationFailureHandler, times(1)).handle(any());
    }

    @Test
    void givenNotExistedUser_ThenAuthenticationFailureHandlerExecute() {
        String notExistedUsername = "notExistedName@test.com";
        String validPassword = "password";

        when(userService.loadUserDetailsByUserName(notExistedUsername))
            .thenReturn(Optional.empty());
        doNothing().when(authenticationFailureHandler).handle(any());

        assertThatThrownBy(() -> authenticationService.authenticate(notExistedUsername, validPassword))
            .isInstanceOf(AppException.class);

        verify(authenticationFailureHandler, times(1)).handle(any());
    }

    @Test
    void givenNotValidUser_ThenAuthenticationFailureHandlerExecute() {
        String existedUsername = "existedName@test.com";
        String validPassword = "password";

        when(userService.loadUserDetailsByUserName(existedUsername))
            .thenReturn(Optional.of(User.builder().build()));
        doThrow(AppException.class).when(userDetailsChecker).check(any());
        doNothing().when(authenticationFailureHandler).handle(any());

        assertThatThrownBy(() -> authenticationService.authenticate(existedUsername, validPassword))
            .isInstanceOf(AppException.class);

        verify(authenticationFailureHandler, times(1)).handle(any());
    }

    @Test
    void givenWrongPassword_ThenAuthenticationFailureHandlerExecute() {
        String existedUsername = "existedName@test.com";
        String validPassword = "password";

        when(userService.loadUserDetailsByUserName(existedUsername))
            .thenReturn(Optional.of(User.builder().build()));
        doNothing().when(userDetailsChecker).check(any());
        doNothing().when(authenticationFailureHandler).handle(any());
        when(userService.loadUserDetailsByUserName(existedUsername))
            .thenReturn(Optional.of(User.builder().build()));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThatThrownBy(() -> authenticationService.authenticate(existedUsername, validPassword))
            .isInstanceOf(AppException.class);

        verify(authenticationFailureHandler, times(1)).handle(any());
    }

    @Test
    void givenValidUsernamePassword_ThenAuthenticationSuccessHandlerExecute() {
        String existedUsername = "existedName@test.com";
        String validPassword = "password";

        when(userService.loadUserDetailsByUserName(existedUsername))
            .thenReturn(Optional.of(User.builder().build()));
        doNothing().when(userDetailsChecker).check(any());
        doNothing().when(authenticationSuccessHandler).handle(any());
        when(userService.loadUserDetailsByUserName(existedUsername))
            .thenReturn(Optional.of(User.builder().build()));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(tokenService.createTokenForUser(any())).thenReturn(any());

        authenticationService.authenticate(existedUsername, validPassword);

        verify(authenticationSuccessHandler, times(1)).handle(any());
        verify(tokenService, times(1)).createTokenForUser(any());
    }
}