package com.ink.studio.tattoo.inkstudiotattoo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.service.OrcamentosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentosController {

	@Autowired
	OrcamentosService os;
	
	@GetMapping("/cadastro")
	public String cadastro(HttpSession session, Model model) {
	    // Suponha que o atributo da sessão é um usuário logado
	    Object usuario = session.getAttribute("usuarioLogado");

	    // Se o usuário estiver logado, você pode adicionar informações ao modelo
	    model.addAttribute("usuario", usuario);

	    // Retorna o nome da view a ser exibida
	    return "orcamentos";
	}


	@PostMapping("/cadastro")
	public String create(@ModelAttribute Orcamentos orcamento) {
		
		os.gravarOrcamento(orcamento);

		return "redirect:usuario/pagina-principal";
	}

}
