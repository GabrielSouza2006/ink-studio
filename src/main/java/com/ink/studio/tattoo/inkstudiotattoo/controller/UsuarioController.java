package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.UsuarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	OrcamentosRepository or;

	// -------------------------- Cadastro --------------------------
	@GetMapping("/cadastro")
	public String cadastro() {

		return "cadastro";
	}

	@PostMapping("/cadastro")
	public String create(Model model, @ModelAttribute Usuario usuario) {

		try {
			usuario.setStatusUsuario("ATIVO");
			usuarioService.gravarUsuario(usuario);
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/usuarios/cadastro";
		}

		return "redirect:/usuarios/login";
	}

	// -------------------------- Login --------------------------
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Usuario usuario, HttpSession session) {
		Usuario userSession = this.usuarioRepository.login(usuario.getCpf(), usuario.getSenha());

		if (userSession != null) {
			// Verifica o status do usuário
			if ("INATIVO".equals(userSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return "login";
			}

			// Se o status for ativo, inicia a sessão do usuário
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);
			return "redirect:/usuarios/pagina-principal";
		}

		model.addAttribute("erro", "usuario ou senha inválidos!");
		return "login";
	}

	@GetMapping("/pagina-principal")
	public String paginaPrincipal() {
		return "pagina-principal";
	}

	@GetMapping("/logoff")
	public String efetuarLogoff(HttpSession session) {

		session.invalidate();

		return ("login");
	}

	// -------------------------- Alterar Usuario --------------------------
	@GetMapping("/perfil")
	public String perfilCliente() {
		return "Perfil-cliente";
	}

	@GetMapping("/editar-cliente")
	public String paginaEditarCliente() {
		return "Editar-cliente";
	}

	@PostMapping("/atualizar/{id}")
	public String atualizarUsuario(@PathVariable Long id, Usuario usuario) {

		usuarioService.atualizarUsuario(id, usuario);

		return "redirect:/usuarios/pagina-principal";
	}

	@PostMapping("/deletar-conta/{id}")
	public String desativarUsuario(@PathVariable Long id) {
		usuarioService.desativarUsuario(id);

		return "redirect:/usuarios/login";
	}

	// -------------------------- Esqueci a senha --------------------------
	@GetMapping("/trocar-senha")
	public String esquiciSenha() {
		return "recuperar-senha";
	}

	@PostMapping("/trocar-senha")
	public String confirirParaTrocarSenha(Model model, Usuario usuario, HttpSession session) {
		Usuario userSession = this.usuarioRepository.trocarSenha(usuario.getCpf(), usuario.getEmail());

		if (userSession != null) {
			session.setAttribute("userSession", userSession);
			model.addAttribute("usuario", userSession);

			return "trocar-senha";
		}
		model.addAttribute("erro", "CPF e senha não correspondem!");
		return "recuperar-senha";
	}

	@PostMapping("/atualizar-senha/{id}")
	public String atualizarSenha(@PathVariable Long id, Usuario usuario) {

		usuarioService.atualizarSenha(id, usuario);

		return "redirect:/usuarios/login";
	}

	// -------------------------- Colsultar agenda --------------------------
	@GetMapping("/minha-agenda")
	public ModelAndView listarAgenda(HttpSession session) {
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
