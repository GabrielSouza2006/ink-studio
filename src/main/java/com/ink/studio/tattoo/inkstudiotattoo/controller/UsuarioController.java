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
			return "redirect:/usuarios/pagina-principal/" + user.getId();
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login";
	}

	// Página principal controller
	@GetMapping("/pagina-principal/{id}")
	public ModelAndView paginaPrincipal(@PathVariable("id") Long id) {
	    ModelAndView mv = new ModelAndView("pagina-principal");
	    Usuario usuario = usuarioRepository.findById(id).orElse(null);
	    mv.addObject("usuario", usuario);

	    return mv;
	}

	// -------------------------- Alterar Usuario --------------------------
	@GetMapping("/perfil/{id}")
	public String perfilCliente() {

		return "Perfil-cliente";
	}

}
