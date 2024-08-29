package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("/cadastro")
	public String create(@ModelAttribute Funcionario funcionario, @RequestParam(value = "file", required = false) MultipartFile file) {

		funcionarioService.gravarFuncionario(funcionario, file);

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
	
	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") long id, 
			HttpServletResponse response, Funcionario funcionario)
			throws ServletException, IOException {

		funcionario = funcionarioService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (funcionario.getFotoPerfil() != null) {
			response.getOutputStream().write(funcionario.getFotoPerfil());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}
}