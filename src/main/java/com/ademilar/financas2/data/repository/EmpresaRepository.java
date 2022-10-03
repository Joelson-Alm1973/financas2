package com.ademilar.financas2.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.data.model.UF;
import com.ademilar.financas2.data.projection.EmpresaProjection;

/*
 * Hierarquia das interfaces DAO
 * 
 * JpaRepository extends  [PagingAndSortingRepository extends [CrudRepository extends Repository]]
 * Logo: JPA Repository é a interface que herda mais métodos
 * => CTRL Clique sobre a interface para ver métodos
 * 
 * Não é possível instanciar objetos de interface, então porque isso funciona?
 */

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa> {

	List<Empresa> findByRazaoSocialContaining(String razaoSocial);

	// busca com ordenação
	List<Empresa> findByRazaoSocialContaining(String razaoSocial, Sort sort);
			
	List<Empresa> findByFantasiaContaining(String fantasia);
	
	List<Empresa> findByCNPJContaining(String cnpj);
	
	List<Empresa> findByIEContaining(String ie);
	
	List<Empresa> findByEmailContaining(String email);
	
	List<Empresa> findByEndereco_CidadeContaining(String cidade);
	
	List<Empresa> findByEndereco_BairroContaining(String bairro);
	
	List<Empresa> findByEndereco_CepContaining(String cep);
	
	List<Empresa> findByEndereco_uf(UF uf);
	
	// busca com paginação
	Page<Empresa> findAll(Pageable pageable);
	
	@Query("SELECT e FROM Empresa e JOIN e.usuario u WHERE u.nome = :nomeUsuario")
	List<Empresa> findAllByUsuario(@Param("nomeUsuario") String nomeUsuario, Pageable pageable);
	
	@Query("SELECT e FROM Empresa e JOIN e.usuario u WHERE u.nome = :nomeUsuario")
	List<Empresa> findAllByUsuario(@Param("nomeUsuario") String nomeUsuario, Sort sort);
	
	@Query (value="SELECT e.id, e.razao_social, e.cnpj FROM Empresa e", nativeQuery=true)
	List<EmpresaProjection> findEmpresaProjecao();
	
	@Procedure("get_total_empresas")
	int getTotalEmpresas();
}

/* Exemplos 

	- Like
		String nome = "%maria%";
		List<Pessoa> findByNomeLike(String nome);
	
	
	- Starting e Ending
		List<Pessoa> findByNomeEndingWith(String nome);
		List<Pessoa> findByNomeStartingWith(String nome);
		
		
	- Null e not Null
		List<Pessoa> findByNomeIsNull();
		List<Pessoa> findByNomeIsNotNull();
	
	
	- Ordenação
		List<Pessoa> findByNomeOrderByNomeAsc(String nome);
	
	
	- Query
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LoalDate.parse("21/06/2000", formatador);
		
		@Query("SELECT p FROM Pessoa p WHERE p.nome = :nome AND p.dataNasc = :data")
		List<Pessoa> findNomeDataNasc(String nome, LocalDate data)
	
	
	- NativeQuery
		@Query(value = "SELECT * FROM Pessoa WHERE nome = ?1", nativeQuery = true)
	  	List<Pessoa> findByNome(String nome);
		
		
	- JPA Named Queries
		@Entity
		@NamedQuery(name = "Pessoa.findByNome", query = "select p from Pessoa p where p.nome = ?1")
		public class Pessoa { 
		
		}
		
		List<Pessoa> findByNome(String nome);
		
	
	- Paginação e ordenação  = interface PagingAndSortingRepository
		  // Obs: JpaRepository estende PagingAndSortingRepository
		  //							     page, size, sort
		  Pageable pageable = PageRequest.of(  1,    5,  Sort.unsorted());
		  Page <Empresa> empresas = empresaRepository.findAll(pageable); 
		  System.out.println("Página atual: " + empresas.getNumber());    // 0..n
		  System.out.println("Total de itens da consulta: " + empresas.getTotalElements());
		  
	- Ordenação: 
		 Pageable pageable = PageRequest.of(  1,    5,  Sort.by(Sort.Direction.ASC, "razaoSocial"));
	
	- Mais em:
		https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
		
 */
