package com.ademilar.financas2.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.ademilar.financas2.data.model.TipoCliente;
import com.ademilar.financas2.data.model.Tipo_estado_civil;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length= 8)
	private TipoCliente tipoCliente;
	
	@Column(length = 55)
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;
	
	@Column(length = 55)
	@NotEmpty(message = "Razão Social é obrigatório")
	private String razao_social;
	
	@Column(length = 20)
	@NotEmpty(message = "CNPJ/CPF é obrigatório")
	private String cnpj_cpf;
	
	@Column(length = 12)
	@NotEmpty(message = "IE/RG é obrigatório")
	private String ie_rg;
	
	@Column(length = 25)
	@NotEmpty(message = "Contato é obrigatório")
	private String contato;
	
	@Column(length = 50)
	@NotEmpty(message = "Email é obrigatório")
	private String email;
	
	@Column(length = 55)
	@NotEmpty(message = "Nome do Pai é obrigatório")
	private String nome_pai;
	
	@Column(length = 55)
	@NotEmpty(message = "Nome da Mãe é obrigatório")
	private String nome_mae;
	
	@Column(length = 35)
	@NotEmpty(message = "Profissão é obrigatório")
	private String profissao;
	
	@Column(length = 35)
	@NotEmpty(message = "Local de trabalho é obrigatório")
	private String local_trabalho;
	
	@Column(length = 10)
	@NotEmpty(message = "Estado Civil é obrigatório")
	private Tipo_estado_civil tipo_estado_civil;
	
	@Column(length = 10)
	@NotEmpty(message = "Conjuge é obrigatório")
	private String conjuge;
	
	private Float limite_credito;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	
	
	
	
	

}
