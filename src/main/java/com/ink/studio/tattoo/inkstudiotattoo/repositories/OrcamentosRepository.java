package com.ink.studio.tattoo.inkstudiotattoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ink.studio.tattoo.inkstudiotattoo.model.Orcamentos;

@Repository
public interface OrcamentosRepository extends JpaRepository<Orcamentos, Long>{

}
