package com.ademilar.financas2.data.projection;

// Interface based Projection
// Representa uma entidade projetada, ou seja, traz apenas os atributos que tenho interesse,
// tornando assim a consulta mais rápida!

// Obs.: Também podemos utilizar uma classe!
// Exemplo: EmpresaDTO onde criamos um construtor, na ordem da query, para receber os atributos
// Obs.: DTO = Data Transfer Object

public interface EmpresaProjection {

	// na ordem da query
	Long getId();
	String getRazao_Social();
	String getCNPJ();
}
