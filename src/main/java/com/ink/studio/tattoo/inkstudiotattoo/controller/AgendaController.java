package com.ink.studio.tattoo.inkstudiotattoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Agenda;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.AgendaRepository;

@Controller
@RequestMapping("/agenda")
public class AgendaController {
	
	@Autowired
	AgendaRepository ar;
	
	@GetMapping("/usuario/{id}")
	public ModelAndView agendaUsuario() {

		ModelAndView mv = new ModelAndView("agenda");
		Iterable<Agenda> agenda = ar.findAll();
		mv.addObject("agenda", agenda);

		return mv;
	}
}
