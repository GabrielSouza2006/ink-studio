package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	UsuarioService usuarioService;
	
	// --------------------------Carregar o formulário de cadastro do usuário (método)--------------------------
	@GetMapping("/cadastro")
	public String cadastro() {
		
		return "cadastro";
	}
	
	@PutMapping("/cadastro")
	public String gravarUsuario() {
		
		return "cadastro";
	}

	
	// --------------------------inserir usuário (método)--------------------------
	@PostMapping("/novo-usuario")
	public String gravarNovoUsuario(Usuario usuario) {

		usuarioRepository.save(usuario);
		
		return "redirect:/inkstudiotattoo/usuarios/login";
	}
	
	
	// --------------------------Altarar Usuario--------------------------
	@GetMapping("/alterar/{id}")
	public String pag() {
		
		return "editar-cliente";
	}
	
	@PostMapping("/alterar/{id}")
	public String alterarUsuario(Usuario usuario) {
		
		usuarioRepository.save(usuario);
		
		return "redirect:/inkstudiotattoo/usuarios/listar";
	}
		
	// Deletar usuário
	@GetMapping("/deletar-usuario/{id}")
	public String deletarUsuario(@PathVariable long id) {

		usuarioRepository.deleteById(id);
			
		return "redirect:/inkstudiotattoo/usuarios/login";
	}
}
