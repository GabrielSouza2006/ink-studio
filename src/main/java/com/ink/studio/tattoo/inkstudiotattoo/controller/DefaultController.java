package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

import java.util.List;

@Controller
@RequestMapping("")
public class DefaultController {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	FuncionarioService funcionarioService;

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/profissionais")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("profissionais");
		Iterable<Funcionario> funcionario = funcionarioRepository.findAll();

		List<Funcionario> funcionariosAtivos = StreamSupport.stream(funcionario.spliterator(), false)
				.filter(f -> "ATIVO".equals(f.getStatusUsuario())).collect(Collectors.toList());

		mv.addObject("funcionario", funcionariosAtivos);

		return mv;
	}

}
