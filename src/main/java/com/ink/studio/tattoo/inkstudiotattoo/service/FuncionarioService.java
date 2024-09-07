package com.ink.studio.tattoo.inkstudiotattoo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;
import com.ink.studio.tattoo.inkstudiotattoo.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository fr;

	public FuncionarioService(FuncionarioRepository fr) {
		this.fr = fr;
	}

	public Funcionario findById(long id) {
		return fr.findById(id).get();
	}

	public boolean existsById(Long id) {
		return fr.existsById(id);
	}

	public void deleteById(Long id) {
		fr.deleteById(id);
	}

	public Funcionario gravarFuncionario(Funcionario funcionario, MultipartFile file) {

		if (fr.existsByCpf(funcionario.getCpf())) {
			throw new IllegalArgumentException("CPF Inválido!");
		}

		if (file != null && file.getSize() > 0) {
			try {
				funcionario.setFotoPerfil(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			funcionario.setFotoPerfil(null);
		}

		return fr.save(funcionario);
	}

	public void atualizarFuncionario(Long id, Funcionario funcionario) {
		Funcionario func = fr.findById(id).orElseThrow(() -> new RuntimeException("Falha"));
		func.setDescricao(funcionario.getDescricao());
		func.setTelefone(funcionario.getTelefone());
		func.setEmail(funcionario.getEmail());
		
		fr.save(func);
	}
	
	public void atualizarSenha(Long id, Funcionario funcionario) {
		Funcionario func = fr.findById(id).orElseThrow(() -> new RuntimeException("Falha"));
		func.setSenha(funcionario.getSenha());
		
		fr.save(func);
	}
	
	public void desativarFuncionario(Long id) {
		Funcionario func = fr.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		func.setStatusUsuario("INATIVO");
		fr.save(func);
	}

}
