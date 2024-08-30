package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.service.OrcamentosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentosController {

	@Autowired
	OrcamentosService os;
	
	@GetMapping("/cadastro")
	public String cadastro() {

		return "orcamentos";
	}

	@PostMapping("/cadastro")
	public String create(@ModelAttribute Orcamentos orcamento) {

		os.gravarOrcamento(orcamento);

		return "redirect:usuario/pagina-principal";
	}

}
