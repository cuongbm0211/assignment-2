package com.example.auth.db.jpa.repositories;

import com.example.auth.db.jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
