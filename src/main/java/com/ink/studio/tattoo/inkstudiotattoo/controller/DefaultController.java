package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DefaultController {

	@GetMapping("/home")
	public String cadastro() {
		return "home";
	}
	
	@GetMapping("/profissionais")
	public String profissionais() {
		return "profissionais";
	}
}
