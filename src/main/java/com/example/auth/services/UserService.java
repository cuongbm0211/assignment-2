package com.example.auth.services;

import com.example.auth.db.jpa.entities.User;
import java.util.Optional;

public interface UserService {

    Optional<User> loadUserDetailsByUserName(String username);

}
