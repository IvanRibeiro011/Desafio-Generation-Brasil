package com.generation.api.generation_brasil_challenge.repository;

import com.generation.api.generation_brasil_challenge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
