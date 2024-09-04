package com.ink.studio.tattoo.inkstudiotattoo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@PostMapping("/cadastro")
	public String create(Model model, @ModelAttribute Usuario usuario) {

		try {
			usuarioService.gravarUsuario(usuario);
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/usuarios/cadastro";
		}

		return "redirect:/usuarios/login";
	}

	// -------------------------- Login --------------------------
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Usuario usuario, HttpSession session) {
		Usuario userSession = this.usuarioRepository.login(usuario.getCpf(), usuario.getSenha());

		if (userSession != null) {
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);
			
			return "pagina-principal";
		}
		
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login";
	}

	// -------------------------- Alterar Usuario --------------------------
	@GetMapping("/perfil/{id}")
	public String perfilCliente() {

		return "Perfil-cliente";
	}
	
	// -------------------------- Orcamento --------------------------
}
