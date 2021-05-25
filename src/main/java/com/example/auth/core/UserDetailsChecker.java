package com.example.auth.core;

import com.example.auth.db.jpa.entities.User;

public interface UserDetailsChecker {

    void check(User toCheck);

}
