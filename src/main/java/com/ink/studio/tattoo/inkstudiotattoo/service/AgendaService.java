package com.ink.studio.tattoo.inkstudiotattoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.studio.tattoo.inkstudiotattoo.model.Agenda;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	AgendaRepository ar;
	
	public AgendaService(AgendaRepository ar) {
		this.ar = ar;
	}
	
	public Agenda findById(long id) {
		return ar.findById(id).get();
	}
	
}
