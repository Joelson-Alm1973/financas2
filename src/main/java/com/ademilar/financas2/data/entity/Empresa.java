package com.ademilar.financas2.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotNull(message = "Razão social de empresa é obrigatória")
	@NotEmpty(message = "Razão social de empresa é obrigatória")
	@Column(length = 70, nullable = false)
	private String razaoSocial;

//	@NotNull(message = "Fantasia de empresa é obrigatória")
	@NotEmpty(message = "Fantasia de empresa é obrigatória")
	@Column(length = 40, nullable = false)
	private String fantasia;

//	@NotNull(message = "CNPJ da empresa é obrigatória")
	@NotEmpty(message = "CNPJ de empresa é obrigatória")
	@Column(length = 15, nullable = false)
	private String CNPJ;

	@NotEmpty(message = "IE da empresa é obrigatória")
	@Column(length = 25)
	private String IE;

	@Column(length = 50)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

//	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
	private List<FoneEmpresa> fones;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PlanoConta> contas;
	
	// LAZY = carregamento ondemand = não carrega do banco até que você precise (é o padrão)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_nome", nullable=true) //nullable=false)
	private Usuario usuario;
	
	
	
	public Empresa() {
		this.fones = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getIE() {
		return IE;
	}

	public void setIE(String IE) {
		this.IE = IE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<FoneEmpresa> getFones() {
		return fones;
	}

	public void setFones(List<FoneEmpresa> fones) {
		this.fones = fones;
	}

	public List<PlanoConta> getContas() {
		return contas;
	}

	public void setContas(List<PlanoConta> contas) {
		this.contas = contas;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		String nome = usuario == null ? "NULL" : usuario.getNome();
		return String.format("ID:%-5d %-40s %-30s %-20s %-10s", id, razaoSocial, fantasia, CNPJ, nome);
	}

}
