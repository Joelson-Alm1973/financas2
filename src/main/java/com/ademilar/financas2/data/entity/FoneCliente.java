package com.ademilar.financas2.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FoneCliente extends Fone {

	@ManyToOne
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
