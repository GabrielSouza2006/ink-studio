	package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
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
	public String create(Model model, @ModelAttribute Funcionario funcionario,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		try {
			funcionario.setStatusUsuario("ATIVO");
			funcionarioService.gravarFuncionario(funcionario, file);
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/funcionarios/cadastro";
		}

		return "redirect:/funcionarios/login";
	}

	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") long id, HttpServletResponse response, Funcionario funcionario)
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

	@GetMapping("/showImageTattoo/{id}")
	@ResponseBody
	public void showImageTattoo(@PathVariable("id") long id, HttpServletResponse response, Funcionario funcionario)
			throws ServletException, IOException {

		funcionario = funcionarioService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (funcionario.getFotoTattoo() != null) {
			response.getOutputStream().write(funcionario.getFotoTattoo());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}

	// Login do funcionario
	@GetMapping("/login")
	public String login() {
		return "login-funcionario";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Funcionario funcionario, HttpSession session) {
		Funcionario userSession = this.funcionarRepository.login(funcionario.getCpf(), funcionario.getSenha());

		if (userSession != null) {
			// Verifica o status do usuário
			if ("INATIVO".equals(userSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return "login-funcionario";
			}

			// Se o status for ativo, inicia a sessão do usuário
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);
			return "pag-principal-func";
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login-funcionario";
	}

	@GetMapping("/logoff")
	public String efetuarLogoff(HttpSession session) {

		session.invalidate();

		return ("login-funcionario");
	}

	// Página principal controller
	@GetMapping("/pagina-principal")
	public String paginaPrincipal() {
		return "pag-principal-func";

	}
	
	// Perfil funcionario
	@GetMapping("/perfil")
	public String perfilFuncionario() {
		return "perfil-func";
	}

	@GetMapping("/editar-funcionario")
	public String paginaEditarFuncionario() {
		return "editar-func";
	}

	@PostMapping("/deletar-conta/{id}")
	public String desativarUsuario(@PathVariable Long id) {
		funcionarioService.desativarFuncionario(id);

		return "redirect:/usuarios/login";
	}

}