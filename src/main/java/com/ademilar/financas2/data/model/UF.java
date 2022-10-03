package com.ademilar.financas2.data.model;

public enum UF {
	AC("Acre"), 
	AL("Alagoas"), 
	AP("Amapá"), 
	AM("Amazonas"), 
	BA("Bahia"), 
	CE("Ceará"), 
	DF("Distrito Federal"),
	ES("Espírito Santo"), 
	GO("Goiás"), 
	MA("Maranhão"), 
	MT("Mato Grosso"), 
	MS("Mato Grosso do Sul"), 
	MG("Minas Gerais"),
	PA("Pará"), 
	PB("Pernambuco"), 
	PR("Paraná"), 
	PE("Pernambuco"), 
	PI("Piauí"), 
	RJ("Rio de Janeiro"),
	RN("Rio Grande do Norte"), 
	RS("Rio Grande do Sul"), 
	RO("Rondônia"), 
	RR("Roraima"), 
	SC("Santa Catarina"),
	SP("São Paulo"), 
	SE("Sergipe"), 
	TO("Tocantins");

	public String descricao;

	private UF(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "PR";
	}
}
