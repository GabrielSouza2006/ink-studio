package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;

@Repository
public interface OrcamentosRepository extends JpaRepository<Orcamentos, Long>{

	// Método para buscar orçamentos pelo ID do funcionário
	@Query(value = "select * from orcamento where id_funcionario = :id_funcionario", nativeQuery = true)
    Iterable<Orcamentos> findByIdFuncionario(Long id_funcionario);
	
}
