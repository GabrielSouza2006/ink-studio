package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;

@Service
public class OrcamentosService {

	@Autowired
	OrcamentosRepository or;

	public OrcamentosService(OrcamentosRepository or) {
		this.or = or;
	}

	public Orcamentos gravarOrcamento(Orcamentos orcamento) {

		return or.save(orcamento);
	}

}
