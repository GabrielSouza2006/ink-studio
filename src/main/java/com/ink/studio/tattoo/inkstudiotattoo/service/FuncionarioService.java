package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	FuncionarioRepository fr;

	public FuncionarioService(FuncionarioRepository fr) {
		this.fr = fr;
	}

	public Funcionario gravarFuncionario(Funcionario funcionario) {

		return fr.save(funcionario);
	}

}
