package com.example.auth.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BcryptPasswordEncoderTest {

    PasswordEncoder passwordEncoder = new BcryptPasswordEncoder();

    @Test
    void givenCorrectPassword_ThenMatches() {
        assertThat(passwordEncoder.matches("pass@123", passwordEncoder.encode("pass@123")))
            .isTrue();
    }

    @Test
    void givenWrongPassword_ThenNotMatches() {
        assertThat(passwordEncoder.matches("pass", passwordEncoder.encode("pass@123")))
            .isFalse();
    }

    @Test
    void givenEmptyPassword_ThenNotMatchPassword() {
        assertThat(passwordEncoder.matches("", passwordEncoder.encode("password")))
            .isFalse();
    }

    @Test
    void givenNullPassword_ThenNotMatchPassword() {
        assertThatIllegalArgumentException().isThrownBy(() -> passwordEncoder.encode(null));
    }
}