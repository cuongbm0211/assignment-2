package com.example.auth.db.jpa.repositories;

import com.example.auth.db.jpa.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
