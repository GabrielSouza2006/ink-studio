package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select * from usuario where cpf = :cpf and senha = :senha", nativeQuery = true)
	public Usuario login(String cpf, String senha);
	
}
