package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FuncionarioRepository fr;
	@Autowired
	FuncionarioService fs;

	@GetMapping("/listar-funcionarios")
	public ModelAndView listarFuncionarios() {

		ModelAndView mv = new ModelAndView("lista-funcionarios");
		Iterable<Funcionario> func = fr.findAll();
		mv.addObject("funcionario", func);

		return mv;
	}

	//@DeleteMapping(path = "/deletar-func/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping("/deletar-func/{id}")
	public String excluirFuncionario(@PathVariable Long id) {
		if (fs.existsById(id)) {
			fs.deleteById(id);
			return "redirect:/admin/listar-funcionarios";
		} else {
			return "redirect:/admin/listar-funcionarios";
		}
	}
	
	@Autowired
	UsuarioRepository ur;
	@Autowired
	UsuarioService us;
	
	@GetMapping("/listar-usuarios")
	public ModelAndView listarUsuarios() {

		ModelAndView mv = new ModelAndView("lista-usuarios");
		Iterable<Usuario> usu = ur.findAll();
		mv.addObject("usuario", usu);

		return mv;
	}

	//@DeleteMapping(path = "/deletar-user/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping("/deletar-user/{id}")
	public String excluirUsuario(@PathVariable Long id) {
		if (ur.existsById(id)) {
			ur.deleteById(id);
			return "redirect:/admin/listar-usuarios";
		} else {
			return "redirect:/admin/listar-usuarios";
		}
	}
}
