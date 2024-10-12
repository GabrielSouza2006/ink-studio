package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select * from usuario where cpf = :cpf and senha = :senha", nativeQuery = true)
	public Usuario login(String cpf, String senha);

	@Query(value = "select * from usuario where cpf = :cpf and email = :email", nativeQuery = true)
	public Usuario trocarSenha(String cpf, String email);

	@Query(value = "select * from usuario where email = :email and senha = :senha", nativeQuery = true)
	public Usuario loginAdmin(String email, String senha);

	boolean existsByCpf(String cpf);

	
	@Query(value = "select * from usuario where email = :email and senha = :senha", nativeQuery = true)
	public Usuario loginMobile(String email, String senha);
	
	public Usuario findByEmailAndSenha(String email, String senha);

	boolean existsByEmail(String email);

	public Usuario findByEmail(String email);
}
