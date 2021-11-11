package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.ListasEntity;
import com.example.error.ResourceDuplicatedEntityException;
import com.example.error.ResourceNotFoundException;
import com.example.repositories.ListasRepository;

@Service
public class ListasService {

	@Autowired
	private ListasRepository listasRepository;

	public ListasEntity save(@RequestBody ListasEntity list) {
		duplicatedList(list);
		return listasRepository.save(list);
	}

	public ListasEntity update(@PathVariable long id, @RequestBody ListasEntity list) {
		verifyListExistsById(id);
		return listasRepository.save(list);
	}

	public List<ListasEntity> findall() {
		verifyAllLists();
		if (listasRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return listasRepository.findAll();
		}
	}

	public Optional<ListasEntity> findById(@PathVariable long id) {
		verifyListExistsById(id);
		if (listasRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return listasRepository.findById(id);
		}
	}

	public ResponseEntity<?> delete(Long id) {
		verifyListExistsById(id);
		if (listasRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			listasRepository.deleteById(id);
			return new ResponseEntity<>("Lista deletada com sucesso!", HttpStatus.OK);
		}
	}

	private void verifyListExistsById(Long id) {
		if (listasRepository.findById(id).isEmpty())
			throw new ResourceNotFoundException(null);
	}

	private void verifyAllLists() {
		if (listasRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		}
	}

	private void duplicatedList(ListasEntity titulo) {
		ListasEntity title = listasRepository.findByTitulo(titulo.getTitulo());

		if (title != null)
			throw new ResourceDuplicatedEntityException("Lista já cadastrada");
	}
}
