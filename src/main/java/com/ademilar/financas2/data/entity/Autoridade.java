package com.ademilar.financas2.data.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Authorities")
public class Autoridade {

	@EmbeddedId
	private AutoridadeId id;

	public AutoridadeId getId() {
		return id;
	}

	public void setId(AutoridadeId id) {
		this.id = id;
	}

}
