package com.ademilar.financas2.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.repository.helper.empresa.EmpresasQueries;


@Repository
// public interface Empresas extends JpaRepository<Empresa, Long>, EmpresasQueries {
public interface Empresas extends JpaRepository<Empresa, Long> {

}
