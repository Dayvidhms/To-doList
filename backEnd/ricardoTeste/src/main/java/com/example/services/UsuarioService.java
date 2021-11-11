package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.UsuarioEntity;
import com.example.error.ResourceDuplicatedEntityException;
import com.example.error.ResourceNotFoundException;
import com.example.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioEntity save(UsuarioEntity user) {
		duplicatedUser(user);
		return usuarioRepository.save(user);
	}

	public List<UsuarioEntity> findall() {
		if (usuarioRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return usuarioRepository.findAll();
		}

	}

	public Optional<UsuarioEntity> findId(Long id) {
		if (usuarioRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return usuarioRepository.findById(id);
		}

	}

	public String delete(Long id) {
		if (usuarioRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			usuarioRepository.deleteById(id);
			return "Usuario " + id + " deletado";
		}

	}

	public UsuarioEntity update(@PathVariable("id") Long id, @RequestBody UsuarioEntity user) {
		VerifyIfExistsID(id);
		duplicatedUser(user);
		return usuarioRepository.save(user);
	}

	private void duplicatedUser(UsuarioEntity email) {
		UsuarioEntity usr = usuarioRepository.findByEmail(email.getEmail());

		if (usr != null)
			throw new ResourceDuplicatedEntityException("Usuario já cadastrado");
	}

	public void VerifyIfExistsID(Long id) {
		if (usuarioRepository.findById(id).isEmpty())
			throw new ResourceDuplicatedEntityException("Usuario " + id + " não encontrado");
	}
}