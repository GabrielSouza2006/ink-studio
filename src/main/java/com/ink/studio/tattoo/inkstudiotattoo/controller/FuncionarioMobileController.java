package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioMobileController {
	
	@Autowired
	FuncionarioService fs;
	
	@GetMapping("/{id}")
	public Funcionario getFuncionario(@PathVariable Long id) {
		return fs.findById(id);
	}

}
