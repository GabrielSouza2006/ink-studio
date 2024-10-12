package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoMobileController {

	@Autowired
	OrcamentosRepository or;
	
	@Autowired
	UsuarioRepository ur;	
	
	@GetMapping("/usuario/{email}")
	public List<Orcamentos> visualizar(@PathVariable String email) {
		
		List<Orcamentos> orcamento = or.findAllByUsuarioAndStatusOrcamento(ur.findByEmail(email), "ATIVO");
		return orcamento;
	}
}
