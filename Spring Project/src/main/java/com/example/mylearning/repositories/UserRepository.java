package com.example.mylearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mylearning.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // No code needed; Spring Data JPA handles it
}
