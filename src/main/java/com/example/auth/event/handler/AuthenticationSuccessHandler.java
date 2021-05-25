package com.example.auth.event.handler;

import com.example.auth.event.AuthenticationSuccessEvent;

public interface AuthenticationSuccessHandler {

    void handle(AuthenticationSuccessEvent event);

}
