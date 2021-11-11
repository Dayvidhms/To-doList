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

import com.example.entities.UsuarioEntity;
import com.example.error.ResourceNotFoundException;
import com.example.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UsuarioEntity user) {
		try {
			return new ResponseEntity<>(usuarioService.save(user), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody UsuarioEntity user) {
		try {
			return new ResponseEntity<>(usuarioService.update(id, user), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<>(usuarioService.findall(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findId(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(usuarioService.findId(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(usuarioService.delete(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

}