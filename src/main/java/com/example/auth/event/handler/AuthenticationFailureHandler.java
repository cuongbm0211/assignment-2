package com.example.auth.event.handler;

import com.example.auth.event.AuthenticationFailureEvent;

public interface AuthenticationFailureHandler {

    void handle(AuthenticationFailureEvent event);

}
