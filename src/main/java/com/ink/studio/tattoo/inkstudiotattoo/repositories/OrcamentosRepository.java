package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;
import com.ink.studio.tattoo.inkstudiotattoo.model.Usuario;

@Repository
public interface OrcamentosRepository extends JpaRepository<Orcamentos, Long>{

	// Método para buscar orçamentos pelo ID do funcionário
	@Query(value = "select * from orcamento where id_funcionario = :id_funcionario", nativeQuery = true)
    Iterable<Orcamentos> findByIdFuncionario(Long id_funcionario);
	
	@Query(value = "select * from orcamento where id_usuario = :id_usuario", nativeQuery = true)
    Iterable<Orcamentos> findByIdUsuariorio(Long id_usuario);

	List<Orcamentos>findAllByUsuarioAndStatusOrcamento(Usuario usuario, String statusOrcamento);
}
