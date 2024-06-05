package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	UsuarioRepository usuarioRepository;

	@RequestMapping("listar")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("index");
		Iterable<Usuario> usuario = usuarioRepository.findAll();
		mv.addObject("usuario", usuario);

		return mv;
	}
}
