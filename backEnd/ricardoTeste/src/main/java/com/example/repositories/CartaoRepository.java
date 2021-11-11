package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.CartaoEntity;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
	CartaoEntity findByTitulo(String titulo);
}
