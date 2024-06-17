package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	FuncionarioService funcService;
	
	// -------------------------- Listar -------------------------- 
	@RequestMapping("listar")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("index");
		Iterable<Usuario> usuario = usuarioRepository.findAll();
		mv.addObject("usuario", usuario);

		return mv;
	}
	
	@GetMapping("/cadastro")
	public String cadastro() {

		return "cadastro";
	}
	
	
	// -------------------------- Cadastro -------------------------- 
	@PostMapping(path = "/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String create(@ModelAttribute Funcionario funcionario) {

		funcService.gravarFunc(funcionario);

		return "redirect:/usuarios/login";
	}
}
