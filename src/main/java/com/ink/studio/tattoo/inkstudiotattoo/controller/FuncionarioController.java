package com.ink.studio.tattoo.inkstudiotattoo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.OrcamentosRepository;
import com.ink.studio.tattoo.inkstudiotattoo.service.FuncionarioService;
import com.ink.studio.tattoo.inkstudiotattoo.service.OrcamentosService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarRepository;
	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	OrcamentosRepository or;
	@Autowired
	OrcamentosService os;

	// Cadastro de funcionario
	@GetMapping("/cadastro")
	public String cadastro() {

		return "cadastro-funcionario";
	}

	@PostMapping("/cadastro")
	public String create(Model model, @ModelAttribute Funcionario funcionario,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		try {
			funcionario.setStatusUsuario("ATIVO");
			funcionarioService.gravarFuncionario(funcionario, file);
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/funcionarios/cadastro";
		}

		return "redirect:/funcionarios/login";
	}

	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") long id, HttpServletResponse response, Funcionario funcionario)
			throws ServletException, IOException {

		funcionario = funcionarioService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (funcionario.getFotoPerfil() != null) {
			response.getOutputStream().write(funcionario.getFotoPerfil());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}

	@GetMapping("/showImageTattoo/{id}")
	@ResponseBody
	public void showImageTattoo(@PathVariable("id") long id, HttpServletResponse response, Funcionario funcionario)
			throws ServletException, IOException {

		funcionario = funcionarioService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (funcionario.getFotoTattoo() != null) {
			response.getOutputStream().write(funcionario.getFotoTattoo());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}

	// Login do funcionario
	@GetMapping("/login")
	public String login() {
		return "login-funcionario";
	}

	@PostMapping("/login")
	public String efetuarLogin(Model model, Funcionario funcionario, HttpSession session) {
		Funcionario funcSession = this.funcionarRepository.login(funcionario.getCpf(), funcionario.getSenha());

		if (funcSession != null) {
			// Verifica o status do usuário
			if ("INATIVO".equals(funcSession.getStatusUsuario())) {
				model.addAttribute("erro", "Essa conta foi deletada!");
				return "login-funcionario";
			}

			// Se o status for ativo, inicia a sessão do usuário
			session.setAttribute("funcSession", funcSession);
			model.addAttribute("usuario", funcSession);
			return "pag-principal-func";
		}
		model.addAttribute("erro", "usuario ou senha inválidos");
		return "login-funcionario";
	}

	@GetMapping("/logoff")
	public String efetuarLogoff(HttpSession session) {

		session.invalidate();

		return ("login-funcionario");
	}

	// Página principal controller
	@GetMapping("/pagina-principal")
	public String paginaPrincipal(HttpSession session) {

		// Recupera o objeto Funcionario logado a partir da sessão
		Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcSession");

		// Verifica se o Funcionario está presente na sessão
		if (funcionarioLogado == null) {
			// Caso o Funcionario não esteja presente, redireciona para uma página de erro
			// ou login
			return ("redirect:/funcionarios/login"); // Exemplo de redirecionamento
		}

		return "pag-principal-func";

	}

	// Perfil funcionario
	@GetMapping("/perfil")
	public String perfilFuncionario(HttpSession session) {

		// Recupera o objeto Funcionario logado a partir da sessão
		Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcSession");

		// Verifica se o Funcionario está presente na sessão
		if (funcionarioLogado == null) {
			// Caso o Funcionario não esteja presente, redireciona para uma página de erro
			// ou login
			return ("redirect:/funcionarios/login"); // Exemplo de redirecionamento
		}

		return "perfil-func";
	}

	@GetMapping("/editar-funcionario")
	public String paginaEditarFuncionario() {
		return "editar-func";
	}

	@PostMapping("/deletar-conta/{id}")
	public String desativarUsuario(@PathVariable Long id) {
		funcionarioService.desativarFuncionario(id);

		return "redirect:/usuarios/login";
	}

	@PostMapping("/atualizar/{id}")
	public String atualizarUsuario(@PathVariable Long id, Funcionario funcionario,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		funcionarioService.atualizarFuncionario(id, funcionario, file);

		return "redirect:/funcionarios/perfil";
	}

	// -------------------------- Esqueci a senha --------------------------
	@GetMapping("/trocar-senha")
	public String esquiciSenha() {
		return "recuperar-senha-funcionario";
	}

	@PostMapping("/trocar-senha")
	public String confirirParaTrocarSenha(Model model, Funcionario funcionario, HttpSession session) {
		Funcionario funcSession = this.funcionarRepository.trocarSenha(funcionario.getCpf(), funcionario.getEmail());

		if (funcSession != null) {
			session.setAttribute("funcSession", funcSession);
			model.addAttribute("funcionario", funcSession);

			return "trocar-senha-funcionario";
		}
		model.addAttribute("erro", "CPF e senha não correspondem!");
		return "recuperar-senha-funcionario";
	}

	@PostMapping("/atualizar-senha/{id}")
	public String atualizarSenha(@PathVariable Long id, Funcionario funcionario) {

		funcionarioService.atualizarSenha(id, funcionario);

		return "redirect:/funcionarios/login";
	}

	// -------------------------- Colsultar orcamentos --------------------------
	@GetMapping("/orcamentos")
	public ModelAndView listarOrcamentos(HttpSession session, Model model) {

		model.addAttribute("orcamentos", new Orcamentos());

		// Recupera o objeto Funcionario logado a partir da sessão
		Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcSession");

		// Verifica se o Funcionario está presente na sessão
		if (funcionarioLogado == null) {
			// Caso o Funcionario não esteja presente, redireciona para uma página de erro
			// ou login
			return new ModelAndView("redirect:/funcionarios/login"); // Exemplo de redirecionamento
		}

		// Recupera o ID do Funcionario logado
		Long idFuncionarioLogado = funcionarioLogado.getId(); // Ajuste aqui

		// Filtra os orçamentos pelo ID do funcionário
		Iterable<Orcamentos> orcamento = or.findByIdFuncionario(idFuncionarioLogado);

		List<Orcamentos> orcamentos = StreamSupport.stream(orcamento.spliterator(), false)
				.filter(f -> "PENDENTE".equals(f.getStatusOrcamento())).collect(Collectors.toList());

		// Cria a ModelAndView e passa os orçamentos filtrados
		ModelAndView mv = new ModelAndView("lista-orcamentos-funcionario");
		mv.addObject("orcamento", orcamento);
		mv.addObject("orcamento", orcamentos);

		return mv;
	}

	@PostMapping("/deletar-orcamento/{id}")
	public String excluirOrcamento(@PathVariable Long id) {
		os.desativarOrcamento(id);
		return "redirect:/funcionarios/orcamentos";
	}

	@PostMapping("/ativar-orcamento/{id}")
	public String ativarOrcamento(@PathVariable Long id, Orcamentos orcamentos) {
		os.ativarOrcamentoFunc(id, orcamentos);
		return "redirect:/funcionarios/orcamentos";
	}

	// -------------------------- Colsultar agenda --------------------------
	@GetMapping("/agenda")
	public ModelAndView listarAgenda(HttpSession session) {
		// Recupera o objeto Funcionario logado a partir da sessão
		Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcSession");

		// Verifica se o Funcionario está presente na sessão
		if (funcionarioLogado == null) {
			// Caso o Funcionario não esteja presente, redireciona para uma página de erro
			// ou login
			return new ModelAndView("redirect:/funcionarios/login"); // Exemplo de redirecionamento
		}

		// Recupera o ID do Funcionario logado
		Long idFuncionarioLogado = funcionarioLogado.getId(); // Ajuste aqui

		// Filtra os orçamentos pelo ID do funcionário
		Iterable<Orcamentos> orcamento = or.findByIdFuncionario(idFuncionarioLogado);

		List<Orcamentos> orcamentos = StreamSupport.stream(orcamento.spliterator(), false)
				.filter(f -> "ATIVO".equals(f.getStatusOrcamento())).collect(Collectors.toList());

		// Cria a ModelAndView e passa os orçamentos filtrados
		ModelAndView mv = new ModelAndView("agenda-funcionario");
		mv.addObject("orcamento", orcamento);
		mv.addObject("orcamento", orcamentos);

		return mv;
	}
}