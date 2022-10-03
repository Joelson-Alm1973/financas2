package com.ademilar.financas2.data.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ademilar.financas2.data.entity.Empresa;


// Spring Data JPA Specifications:
// Ajuda a criar consultas dinâmicas com base em requisitos em tempo de execução.
// Permite uma combinação dos atributos ou propriedades de uma entidade (classe), criando uma consulta.

public class EmpresaSpecification {

	public static Specification<Empresa> razaoSocial(String razaoSocial){
		// critério por like 
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("razaoSocial"), "%" + razaoSocial + "%");
	}
	
	public static Specification<Empresa> fantasia(String fantasia){
		// critério por like
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("fantasia"), "%" + fantasia + "%");
	}
	
	public static Specification<Empresa> cnpj(String cnpj){
		// critério por igualdade
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("CNPJ"), cnpj);
	}
	
	
	/*
	 * Mais exemplos:
	 * 
	 * - busca por valor numérico: 
	 * 	 criteriaBuilder.greaterThan(root.get("valor"), valor);
	 * 
	 * - busca por data:
	 *   criteriaBuilder.greaterThan(root.get("data"), data);
	 */
}
