package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.studio.tattoo.inkstudiotattoo.model.FaleConosco;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FaleConoscoRepository;

@Service
public class FaleConoscoService {
	
	@Autowired
	FaleConoscoRepository fcr;
	
	public FaleConosco gravarMensagem(FaleConosco mensagem) {

		return fcr.save(mensagem);
	}

}
