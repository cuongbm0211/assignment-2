package com.example.auth.db.jpa.repositories;

import com.example.auth.db.jpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
}
