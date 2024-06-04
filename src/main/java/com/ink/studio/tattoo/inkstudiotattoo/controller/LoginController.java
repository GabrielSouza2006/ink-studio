package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;

@Controller
@RequestMapping("/inkstudiotattoo/usuarios")
public class LoginController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String efetuarLogin(Model model, Usuario usuario) {
		Usuario user = this.ur.login(usuario.getCpf(), usuario.getSenha());
		if(user != null) {
			return "redirect:/inkstudiotattoo/usuarios/pagina-principal/{id}";
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login";
	}
}
