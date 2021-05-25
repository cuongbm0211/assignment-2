package com.example.auth.services;

import com.example.auth.db.jpa.entities.User;
import com.example.auth.db.jpa.repositories.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> loadUserDetailsByUserName(String username) {
        return userRepository.findByUsername(username);    }
}
