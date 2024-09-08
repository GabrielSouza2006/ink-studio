package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.studio.tattoo.inkstudiotattoo.model.FaleConosco;
import com.ink.studio.tattoo.inkstudiotattoo.service.FaleConoscoService;

@Controller
@RequestMapping("/contato")
public class FaleConoscoController {

	@Autowired
	FaleConoscoService fcs;
	
	@GetMapping("/fale-conosco")
	public String faleConosco(Model model) {
		
		model.addAttribute("mensagem", new FaleConosco());
		
		return "FaleConosco";
	}
	
	@PostMapping("/fale-conosco")
	public String faleConoscoGravar(Model model, @ModelAttribute FaleConosco mensagem) {
		
		fcs.gravarMensagem(mensagem);
		
		return "redirect:/contato/obrigado";
	}
	
	@GetMapping("/obrigado")
	public String agradecimentos() {
		return "obrigado-faleConosco";
	}
	
}
