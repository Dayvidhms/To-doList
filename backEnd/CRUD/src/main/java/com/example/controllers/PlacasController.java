package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.PlacasEntity;
import com.example.error.ResourceNotFoundException;
import com.example.services.PlacasService;

@RestController
@RequestMapping("/placas")
public class PlacasController {
	@Autowired
	private PlacasService placasService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlacasEntity placa) {
		try {
			return new ResponseEntity<>(placasService.save(placa), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody PlacasEntity placa) {
		try {
			return new ResponseEntity<>(placasService.update(id, placa), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<>(placasService.findall(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<>(placasService.findId(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(placasService.delete(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
}
