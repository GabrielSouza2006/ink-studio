package com.ink.studio.tattoo.inkstudiotattoo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;
import com.ink.studio.tattoo.inkstudiotattoo.service.OrcamentosService;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FuncionarioRepository fr;
	@Autowired
	FuncionarioService fs;
	
	@Autowired
	UsuarioRepository ur;
	@Autowired
	UsuarioService us;
	
	@Autowired
	OrcamentosRepository or;
	@Autowired
	OrcamentosService os;

	@GetMapping("/login")
	public String loginPagina() {
		return "login-admin";
	}

	@PostMapping("/login")
	public String login(Model model, Usuario usuario, HttpSession session) {
		Usuario userSession = this.ur.loginAdmin(usuario.getEmail(), usuario.getSenha());

		if (userSession != null) {
			// Verifica o status do usuário
			if ("INATIVO".equals(userSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return "login-admin";
			} else if ("ADMIN".equals(userSession.getStatusUsuario())) {
				session.setAttribute("userSession", userSession);
				model.addAttribute("funcionario", userSession);
				return "redirect:/admin/pagina-principal";
			}
		}

		model.addAttribute("erro", "usuario ou senha inválidos!");
		return "login-admin";
	}
	
	@GetMapping("/pagina-principal")
	public String paginaPrincipal() {
		return "pagina-principal-admin";
	}

	// ---------------- Funcionarios
	
	@GetMapping("/listar-funcionarios")
	public ModelAndView listarFuncionarios() {

		ModelAndView mv = new ModelAndView("lista-funcionarios");
		Iterable<Funcionario> func = fr.findAll();
		mv.addObject("funcionario", func);

		return mv;
	}

	@PostMapping("/deletar-func/{id}")
	public String excluirFuncionario(@PathVariable Long id) {
		fs.desativarFuncionario(id);
		return "redirect:/admin/listar-funcionarios";
	}
	@PostMapping("/ativar-func/{id}")
	public String ativarFuncionario(@PathVariable Long id) {
		fs.ativarFuncionario(id);
		return "redirect:/admin/listar-funcionarios";
	}
	
	// ---------------- Usuarios

	@GetMapping("/listar-usuarios")
	public ModelAndView listarUsuarios() {

		ModelAndView mv = new ModelAndView("lista-usuarios");
		Iterable<Usuario> usu = ur.findAll();
		mv.addObject("usuario", usu);

		return mv;
	}

	@PostMapping("/deletar-user/{id}")
	public String excluirUsuario(@PathVariable Long id) {
		us.desativarUsuario(id);
		return "redirect:/admin/listar-usuarios";
	}
	
	@PostMapping("/ativar-user/{id}")
	public String ativarUsuario(@PathVariable Long id) {
		us.ativarUsuario(id);
		return "redirect:/admin/listar-usuarios";
	}
	
	// ---------------- Orcamentos
	@GetMapping("/listar-orcamentos")
	public ModelAndView listarOrcamentos() {

		ModelAndView mv = new ModelAndView("lista-orcamentos");
		Iterable<Orcamentos> orcamento = or.findAll();
		mv.addObject("orcamento", orcamento);

		return mv;
	}

	@PostMapping("/deletar-orcamento/{id}")
	public String excluirOrcamento(@PathVariable Long id) {
		os.desativarOrcamento(id);
		return "redirect:/admin/listar-orcamentos";
	}
	
	@PostMapping("/ativar-orcamento/{id}")
	public String ativarOrcamentos(@PathVariable Long id) {
		os.ativarOrcamento(id);
		return "redirect:/admin/listar-orcamentos";
	}

}
