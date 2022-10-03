package com.ademilar.financas2.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ademilar.financas2.data.model.TipoConta;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PlanoConta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Descrição é obrigatória")
	@Column(length = 20, nullable = false)
	private String descricao;
	
	@NotEmpty(message = "Num.Conta é obrigatória")
	@Column(length = 10, nullable = false)
	private String numConta;

	@Enumerated(EnumType.STRING)
//	@NotEmpty(message = "Tipo Conta é obrigatória")
	@NotNull(message = "Tipo Conta é obrigatória")	
	@Column(length = 9, nullable = false)
	private TipoConta tipoConta;

	@ManyToOne
	private Empresa empresa;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
}
