package com.example.auth.application.api.controller;

import com.example.auth.application.api.dto.AuthenticationRequest;
import com.example.auth.application.api.dto.AuthenticationResponse;
import com.example.auth.core.rest.response.BaseResponse;
import com.example.auth.db.jpa.entities.SecurityToken;
import com.example.auth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authentication")
    BaseResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        SecurityToken authenticate = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return BaseResponse.ofSucceeded(new AuthenticationResponse(authenticate));
    }
}
