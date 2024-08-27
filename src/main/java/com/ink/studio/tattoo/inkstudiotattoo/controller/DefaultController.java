package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

@Controller
@RequestMapping("")
public class DefaultController {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	FuncionarioService funcionarioService;

	@GetMapping("/home")
	public String cadastro() {
		return "home";
	}

	@GetMapping("/profissionais")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("profissionais");
		Iterable<Funcionario> funcionario = funcionarioRepository.findAll();
		mv.addObject("funcionario", funcionario);

		return mv;
	}

}
