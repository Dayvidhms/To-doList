package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.CartaoEntity;
import com.example.error.ResourceDuplicatedEntityException;
import com.example.error.ResourceNotFoundException;
import com.example.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	public CartaoEntity save(@RequestBody CartaoEntity card) {
		duplicatedCartao(card);
		return cartaoRepository.save(card);
	}

	public CartaoEntity update(@PathVariable long id, @RequestBody CartaoEntity card) {
		verifyListExistsById(id);
		return cartaoRepository.save(card);
	}

	public List<CartaoEntity> findall() {
		verifyAllLists();
		if (cartaoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return cartaoRepository.findAll();
		}
	}

	public Optional<CartaoEntity> findId(@PathVariable long id) {
		verifyListExistsById(id);
		if (cartaoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return cartaoRepository.findById(id);
		}
	}

	public String delete(Long id) {
		verifyListExistsById(id);
		if (cartaoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			cartaoRepository.deleteById(id);
			return "Usuario " + id + " deletado";
		}
	}
	
	private void verifyListExistsById(Long id) {
		if (cartaoRepository.findById(id).isEmpty())
			throw new ResourceNotFoundException("Lista " + id + " não encontrada !");
	}

	private void verifyAllLists() {
		if (cartaoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException("Não existem listas Cadastrados");
		}
	}

	private void duplicatedCartao(CartaoEntity titulo) {
		CartaoEntity title = cartaoRepository.findByTitulo(titulo.getTitulo());

		if (title != null)
			throw new ResourceDuplicatedEntityException("Cartão já cadastrado");
	}
}