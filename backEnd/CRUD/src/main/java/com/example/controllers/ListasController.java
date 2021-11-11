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

import com.example.entities.ListasEntity;
import com.example.error.ResourceNotFoundException;
import com.example.services.ListasService;

@RestController
@RequestMapping("/listas")
public class ListasController {

	@Autowired
	private ListasService listasService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ListasEntity list) {
		try {
			return new ResponseEntity<>(listasService.save(list), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@PutMapping
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody ListasEntity list) {
		try {
			return new ResponseEntity<>(listasService.update(id, list), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping
	public ResponseEntity<?> find(){
		try {	
				return new ResponseEntity<>(listasService.findall(), HttpStatus.OK);
			} catch (ResourceNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}	
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<?> findById(@PathVariable long id){
		try {	
				return new ResponseEntity<>(listasService.findById(id), HttpStatus.OK);
			} catch (ResourceNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {	
				return new ResponseEntity<>(listasService.delete(id), HttpStatus.OK);
			} catch (ResourceNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
	}
}
