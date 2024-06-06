package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository ur;
	
	public UsuarioService(UsuarioRepository ur) {
		this.ur = ur;
	}



	@PutMapping
	public Usuario gravarUsuario(Usuario usuario) {
		
		usuario.setNome(null);
		usuario.setEmail(null);
		usuario.setNascimento(null);
		usuario.setCpf(null);
		usuario.setSenha(null);
		
		return ur.save(usuario);
	}
	

}
