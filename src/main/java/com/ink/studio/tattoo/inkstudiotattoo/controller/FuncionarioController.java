package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarRepository;
	@Autowired
	FuncionarioService funcionarioService;

	// Cadastro de funcionario
	@GetMapping("/cadastro")
	public String cadastro() {

		return "cadastro-funcionario";
	}

	@PostMapping(path = "/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String create(@ModelAttribute Funcionario funcionario) {

		funcionarioService.gravarFuncionario(funcionario);

		return "redirect:/funcionarios/login";
	}

	// Login do funcionario
	@GetMapping("/login")
	public String login() {
		return "login-funcionario";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Funcionario funcionario) {
		Funcionario user = this.funcionarRepository.login(funcionario.getCpf(), funcionario.getSenha());
		if (user != null) {
			return "redirect:/funcionarios/pagina-principal";
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login-funcionario";
	}

	// Página principal controller
	@GetMapping("/pagina-principal")
	public String paginaPrincipal() {
		return "pag-principal-func";

	}
}