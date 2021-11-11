package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	UsuarioEntity findByEmail(String email);
}
