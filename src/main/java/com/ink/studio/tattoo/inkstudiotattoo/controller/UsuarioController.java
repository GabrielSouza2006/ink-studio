package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;

	// -------------------------- Cadastro --------------------------
	@GetMapping("/cadastro")
	public String cadastro() {

		return "cadastro";
	}

	@PostMapping(path = "/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String create(@ModelAttribute Usuario usuario) {

		usuarioService.gravarUsuario(usuario);

		return "redirect:/usuarios/login";
	}
	
	// -------------------------- Login --------------------------
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Usuario usuario) {
		Usuario user = this.usuarioRepository.login(usuario.getCpf(), usuario.getSenha());
		if (user != null) {
			return "redirect:/usuarios/pagina-principal";
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login";
	}

	// Página principal controller
	@GetMapping("/pagina-principal")
	public String paginaPrincipal() {
		return "pagina-principal";
	}

	// -------------------------- Alterar Usuario --------------------------
	@GetMapping("/alterar")
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
