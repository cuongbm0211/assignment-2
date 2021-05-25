package com.example.auth.services;

import com.example.auth.db.jpa.entities.SecurityToken;
import com.example.auth.db.jpa.entities.User;

public interface TokenService {

    SecurityToken createTokenForUser(User user);

}
