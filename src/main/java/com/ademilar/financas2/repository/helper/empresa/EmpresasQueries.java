package com.ademilar.financas2.repository.helper.empresa;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.repository.filter.EmpresaFilter;


public interface EmpresasQueries {
	
	public List<Empresa> filtrar(EmpresaFilter filtro, Pageable pageable);
	
}
