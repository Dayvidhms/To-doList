package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PlacasEntity;
import com.example.entities.UsuarioEntity;

public interface PlacasRepository extends JpaRepository<PlacasEntity, Long> {
	UsuarioEntity findByNome(String nome);
}
