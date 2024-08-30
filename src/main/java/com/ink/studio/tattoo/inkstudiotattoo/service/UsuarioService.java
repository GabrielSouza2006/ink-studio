package com.ink.studio.tattoo.inkstudiotattoo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository ur;

	public UsuarioService(UsuarioRepository ur) {
		this.ur = ur;
	}

	public Usuario gravarUsuario(Usuario usuario) {

		if (ur.existsByCpf(usuario.getCpf())) {
			throw new IllegalArgumentException("CPF Inválido!");
		}
		
		return ur.save(usuario);
	}

	public boolean existsById(Long id) {
		return ur.existsById(id);
	}

	public void deleteById(Long id) {
		ur.deleteById(id);
	}

}
