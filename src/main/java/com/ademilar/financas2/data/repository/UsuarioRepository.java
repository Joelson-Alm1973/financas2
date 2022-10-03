package com.ademilar.financas2.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ademilar.financas2.data.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, String>{

	Usuario findByNome(String nome);
		
}
