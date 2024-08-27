package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService us;

	@GetMapping("/listar")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("index");
		Iterable<Usuario> usuario = usuarioRepository.findAll();
		mv.addObject("usuario", usuario);

		return mv;
	}

	@DeleteMapping(path = "/deletar/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String excluirUsuario(@PathVariable Long id) {
		if (us.existsById(id)) {
			us.deleteById(id);
			return "redirect:/admin/listar";
		} else {
			return "redirect:/admin/listar";
		}
	}
}
