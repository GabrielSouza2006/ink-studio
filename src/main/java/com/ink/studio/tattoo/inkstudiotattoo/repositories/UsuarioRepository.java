package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select * from usuario where cpf = :cpf and senha = :senha", nativeQuery = true)
	public Usuario login(String cpf, String senha);
	
	@Query(value = "select * from usuario where cpf = :cpf and email = :email", nativeQuery = true)
	public Usuario trocarSenha(String cpf, String email);

	boolean existsByCpf(String cpf);
}
