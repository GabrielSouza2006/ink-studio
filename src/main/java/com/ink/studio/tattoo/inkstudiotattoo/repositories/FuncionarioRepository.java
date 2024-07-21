package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ink.studio.tattoo.inkstudiotattoo.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query(value = "select * from funcionario where cpf = :cpf and senha = :senha", nativeQuery = true)
	public Funcionario login(String cpf, String senha);
}
