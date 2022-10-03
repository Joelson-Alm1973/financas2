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

import com.ademilar.financas2.data.model.TipoFone;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class Fone {

	// Obs.: TABLE_PER_CLASS não permite GenerationType.IDENTITY
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private TipoFone tipo;
	
	@Column(length = 15)
	private String numero;
	
	@Column(length = 4)
	private String ramal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoFone getTipo() {
		return tipo;
	}

	public void setTipo(TipoFone tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}


}
