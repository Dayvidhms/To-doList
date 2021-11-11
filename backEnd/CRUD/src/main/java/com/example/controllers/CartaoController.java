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

import com.example.entities.CartaoEntity;
import com.example.error.ResourceNotFoundException;
import com.example.services.CartaoService;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CartaoEntity card) {
		try {
			return new ResponseEntity<>(cartaoService.save(card), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody CartaoEntity card) {
		try {
			return new ResponseEntity<>(cartaoService.update(id, card), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<>(cartaoService.findall(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<>(cartaoService.findId(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(cartaoService.delete(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
}
