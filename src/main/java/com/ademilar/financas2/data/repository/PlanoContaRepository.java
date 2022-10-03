package com.ademilar.financas2.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ademilar.financas2.data.entity.PlanoConta;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, String>{
	
	PlanoConta findByDescricao(String descricao);

}
