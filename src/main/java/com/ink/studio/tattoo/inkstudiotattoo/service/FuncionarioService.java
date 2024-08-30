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

}
