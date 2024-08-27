package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.net.http.HttpHeaders;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;

@Controller
@RequestMapping("")
public class DefaultController {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	FuncionarioService funcionarioService;

	@GetMapping("/home")
	public String cadastro() {
		return "home";
	}

	@GetMapping("/profissionais")
	public ModelAndView listarUsuario() {

		ModelAndView mv = new ModelAndView("profissionais");
		Iterable<Funcionario> funcionario = funcionarioRepository.findAll();
		mv.addObject("funcionario", funcionario);

		return mv;
	}

	@GetMapping("/imagem/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getPhoto(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.getPersonById(id).orElseThrow(() -> new RuntimeException("Funcionario not found"));

		if (funcionario.getImagem() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ByteArrayResource resource = new ByteArrayResource(funcionario.getImagem());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // ou o tipo adequado da sua imagem
				.contentLength(funcionario.getImagem().length).body(resource);
	}
}
