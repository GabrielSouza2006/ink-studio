package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Agenda;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.AgendaRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.AgendaService;

@Controller
@RequestMapping("/agenda")
public class AgendaController {
	
	@Autowired
	AgendaRepository ar;
	@Autowired
	AgendaService as;
	
	@GetMapping("/minha-agenda")
	public ModelAndView cadastro(Model model) {
		
		ModelAndView mv = new ModelAndView("agenda-usuario");
		Iterable<Agenda> agenda = ar.findAll();
		
		List<Agenda> agendaAtiva = StreamSupport.stream(agenda.spliterator(), false)
				.filter(agend -> "ATIVO".equals(agend.getStatusAgenda())).collect(Collectors.toList());

		mv.addObject("agenda", agendaAtiva);
		
		return mv;
	}
	
	@PostMapping("/deletar-agenda/{id}")
	public String deletarAgenda(Long id) {
		
		as.desativarAgenda(id);
		
		return "redirect:/agenda/minha-agenda";
	}
}
