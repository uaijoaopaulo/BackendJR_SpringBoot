package com.example.backendJR.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendJR.modelo.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByEmail(String email);
}
