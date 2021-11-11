package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ListasEntity;

public interface ListasRepository extends JpaRepository<ListasEntity, Long>{
	ListasEntity findByTitulo(String titulo);
}
