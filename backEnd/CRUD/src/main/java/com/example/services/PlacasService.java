package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.PlacasEntity;
import com.example.entities.UsuarioEntity;
import com.example.error.ResourceDuplicatedEntityException;
import com.example.error.ResourceNotFoundException;
import com.example.repositories.PlacasRepository;

@Service
public class PlacasService {

	@Autowired
	private PlacasRepository placasRepository;

	public PlacasEntity save(@RequestBody PlacasEntity card) {
		duplicatedPlacas(card);
		return placasRepository.save(card);
	}

	public PlacasEntity update(@PathVariable long id, @RequestBody PlacasEntity placa) {
		verifyPlacaExistsById(id);
		return placasRepository.save(placa);
	}

	public List<PlacasEntity> findall() {
		verifyAllPlacas();
		if (placasRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return placasRepository.findAll();
		}
	}

	public Optional<PlacasEntity> findId(@PathVariable long id) {
		verifyPlacaExistsById(id);
		if (placasRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return placasRepository.findById(id);
		}
	}

	public String delete(Long id) {
		verifyPlacaExistsById(id);
		if (placasRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			placasRepository.deleteById(id);
			return "Usuario " + id + " deletado";
		}
	}
	
	private void verifyPlacaExistsById(Long id) {
		if (placasRepository.findById(id).isEmpty())
			throw new ResourceNotFoundException("Lista " + id + " não encontrada !");
	}

	private void verifyAllPlacas() {
		if (placasRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException("Não existem listas Cadastrados");
		}
	}

	private void duplicatedPlacas(PlacasEntity nome) {
		UsuarioEntity name = placasRepository.findByNome(nome.getNome());

		if (name != null)
			throw new ResourceDuplicatedEntityException("Placa já cadastrada");
	}
}
