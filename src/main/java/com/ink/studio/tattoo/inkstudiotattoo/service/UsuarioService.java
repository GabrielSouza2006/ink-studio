package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		return ur.save(usuario);
	}
	
}
