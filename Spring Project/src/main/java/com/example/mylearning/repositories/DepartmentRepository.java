package com.example.mylearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mylearning.entities.Department;
import com.example.mylearning.entities.User;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // No code needed; Spring Data JPA handles it
}
