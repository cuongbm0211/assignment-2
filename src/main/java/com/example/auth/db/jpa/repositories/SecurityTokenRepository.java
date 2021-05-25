package com.example.auth.db.jpa.repositories;

import com.example.auth.db.jpa.entities.SecurityToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityTokenRepository extends JpaRepository<SecurityToken, Long> {

}
