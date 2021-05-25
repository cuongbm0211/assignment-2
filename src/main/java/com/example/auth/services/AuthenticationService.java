package com.example.auth.services;

import com.example.auth.db.jpa.entities.SecurityToken;

public interface AuthenticationService {

    SecurityToken authenticate(String username, String password);

}
