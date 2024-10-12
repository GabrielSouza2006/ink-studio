package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioMobileController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	@Autowired
	OrcamentosRepository or;
	
	@GetMapping("/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		return usuarioService.findById(id).get();
	}

	@PostMapping("/loginmobile")
	public Usuario efetuarLoginMobile(Model model, Usuario usuario, HttpSession session) {
		Usuario userSession = this.usuarioRepository.loginMobile(usuario.getEmail(), usuario.getSenha());

		if (userSession != null) {
			// Verifica o status do usuário
			if ("INATIVO".equals(userSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return null;
			}

			// Se o status for ativo, inicia a sessão do usuário
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);
			return userSession;
		}

		model.addAttribute("erro", "usuario ou senha inválidos!");
		return null;

	}

	@GetMapping("/minha-agenda-mobile")
	public ModelAndView listarAgendaMobile(HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("userSession");

		if (usuarioLogado == null) {
			return new ModelAndView("redirect:/usuarios/login");
		}

		Long idUsuarioLogado = usuarioLogado.getId();

		Iterable<Orcamentos> orcamento = or.findByIdUsuariorio(idUsuarioLogado);

		List<Orcamentos> orcamentos = StreamSupport.stream(orcamento.spliterator(), false)
				.filter(f -> "ATIVO".equals(f.getStatusOrcamento())).collect(Collectors.toList());

		// Cria a ModelAndView e passa os orçamentos filtrados
		ModelAndView mv = new ModelAndView("agenda-usuario");
		mv.addObject("orcamento", orcamento);
		mv.addObject("orcamento", orcamentos);

		return mv;
	}
}
