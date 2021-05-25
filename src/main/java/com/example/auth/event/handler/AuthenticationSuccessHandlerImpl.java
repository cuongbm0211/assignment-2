package com.example.auth.event.handler;

import com.example.auth.event.AuthenticationSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void handle(AuthenticationSuccessEvent event) {
        log.info("User {} login success", event.getUsername());
        // todo: will log user_activity to db
    }
}
