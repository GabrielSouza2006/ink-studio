package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/orcamentos")
public class OrcamentosController {

	@GetMapping("/cadastro")
	public String cadastro() {
		return "orcamentos";
	}
	
}
