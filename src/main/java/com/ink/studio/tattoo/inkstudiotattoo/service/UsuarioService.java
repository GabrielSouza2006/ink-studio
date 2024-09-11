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

		if (ur.existsByCpf(usuario.getCpf())) {
			throw new IllegalArgumentException("CPF Inválido!");
		}

		return ur.save(usuario);
	}

	public boolean existsById(Long id) {
		return ur.existsById(id);
	}

	public void atualizarUsuario(Long id, Usuario usuario) {
		Usuario user = ur.findById(id).orElseThrow(() -> new RuntimeException("Falha"));
		user.setTelefone(usuario.getTelefone());
		user.setEmail(usuario.getEmail());

		ur.save(user);
	}

	public void atualizarSenha(Long id, Usuario usuario) {
		Usuario user = ur.findById(id).orElseThrow(() -> new RuntimeException("Falha"));
		user.setSenha(usuario.getSenha());

		ur.save(user);
	}

	public void desativarUsuario(Long id) {
		Usuario usuario = ur.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		usuario.setStatusUsuario("INATIVO");
		ur.save(usuario);
	}

	public void ativarUsuario(Long id) {
		Usuario usuario = ur.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		usuario.setStatusUsuario("ATIVO");
		ur.save(usuario);
	}

}
