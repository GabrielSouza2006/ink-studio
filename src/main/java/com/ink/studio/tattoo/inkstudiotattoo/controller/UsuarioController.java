package com.ink.studio.tattoo.inkstudiotattoo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
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
			usuario.setStatusUsuario("ATIVO");
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
			// Verifica o status do usuário
			if ("INATIVO".equals(userSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return "login";
			}

			// Se o status for ativo, inicia a sessão do usuário
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);
			return "pagina-principal";
		}

		model.addAttribute("erro", "usuario ou senha inválidos!");
		return "login";
	}

	@GetMapping("/logoff")
	public String efetuarLogoff(HttpSession session) {

		session.invalidate();

		return ("login");
	}

	// -------------------------- Alterar Usuario --------------------------
	@GetMapping("/perfil")
	public String perfilCliente() {
		return "Perfil-cliente";
	}

	@GetMapping("/editar-cliente")
	public String paginaEditarCliente() {
		return "editar-func";
	}

	@PutMapping("/editar-cliente")
	public String editarCliente() {

		return "perfil-cliente";
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarUsuario(@PathVariable Long id, Usuario usuario) {
		
		usuarioService.atualizarUsuario(id, usuario);

		return "redirect:/usuarios/perfil";
	}

	@PostMapping("/deletar-conta/{id}")
	public String desativarUsuario(@PathVariable Long id) {
		usuarioService.desativarUsuario(id);

		return "redirect:/usuarios/login";
	}
}
