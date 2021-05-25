package com.example.auth.event.handler;

import com.example.auth.event.AuthenticationFailureEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void handle(AuthenticationFailureEvent event) {
        log.info("User {} login failure", event.getUsername());
        // todo: will log user_activity to db
    }
}
