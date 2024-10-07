package com.ink.studio.tattoo.inkstudiotattoo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.OrcamentosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentosController {

	@Autowired
	OrcamentosService os;
	@Autowired
	FuncionarioRepository fr;

	@GetMapping("/cadastro")
	public String cadastro(Model model, HttpSession session) {

		Usuario usuarioLogado = (Usuario) session.getAttribute("userSession");

		if (usuarioLogado == null) {
			return ("redirect:/usuarios/login");
		}

		Iterable<Funcionario> func = fr.findAll();

		model.addAttribute("funcionarios", func);

		model.addAttribute("orcamento", new Orcamentos());

		return "orcamentos";
	}

	@PostMapping("/cadastro")
	public String create(@ModelAttribute Orcamentos orcamento, HttpSession session) {

		Usuario usuarioLogado = (Usuario) session.getAttribute("userSession");

		orcamento.setUsuario(usuarioLogado);
		orcamento.setStatusOrcamento("PENDENTE");

		os.gravarOrcamento(orcamento);

		return "redirect:/orcamentos/obrigado";
	}

	@GetMapping("obrigado")
	public String obrigado() {
		return "obrigado";
	}

}
