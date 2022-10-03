package com.ademilar.financas2.repository.filter;

public class EmpresaFilter {

	private String Nome_Empresa;
	private String Fantasia_Empresa;
	private String CNPJ_Empresa;
	private String IE_Empresa;

	public String getNome_Empresa() {
		return Nome_Empresa;
	}

	public void setNome_Empresa(String nome_Empresa) {
		Nome_Empresa = nome_Empresa;
	}

	public String getFantasia_Empresa() {
		return Fantasia_Empresa;
	}

	public void setFantasia_Empresa(String fantasia_Empresa) {
		Fantasia_Empresa = fantasia_Empresa;
	}

	public String getCNPJ_Empresa() {
		return CNPJ_Empresa;
	}

	public void setCNPJ_Empresa(String cNPJ_Empresa) {
		CNPJ_Empresa = cNPJ_Empresa;
	}

	public String getIE_Empresa() {
		return IE_Empresa;
	}

	public void setIE_Empresa(String iE_Empresa) {
		IE_Empresa = iE_Empresa;
	}
}