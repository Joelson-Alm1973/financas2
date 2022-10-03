package com.ademilar.financas2.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.data.repository.EmpresaRepository;
import com.ademilar.financas2.data.specification.EmpresaSpecification;

@Service
public class RelatorioDinamicoEmpresaService {

	private final EmpresaRepository empresaRepository;

	public RelatorioDinamicoEmpresaService(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	public void teste() {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite a razão social (pode ser parcial) ou NULL: ");
		String razaoSocial = teclado.nextLine();
		
		if (razaoSocial.equalsIgnoreCase("NULL")) {
			razaoSocial = null;
		}
		
		System.out.println("Digite o nome fantasia (pode ser parcial) ou NULL: ");
		String fantasia = teclado.nextLine();
		
		if (fantasia.equalsIgnoreCase("NULL")) {
			fantasia = null;
		}
		
		System.out.println("Digite o CNPJ completo ou NULL: ");
		String cnpj = teclado.next();
		
		if (cnpj.equalsIgnoreCase("NULL")) {
			cnpj = null;
		}
		
		List<Empresa> lista = empresaRepository.findAll(Specification
			.where(
				EmpresaSpecification.razaoSocial(razaoSocial))
				.or(EmpresaSpecification.fantasia(fantasia))
				.or(EmpresaSpecification.cnpj(cnpj))
			);
		
			lista.forEach(System.out::println);
	}
	
	
	
}
