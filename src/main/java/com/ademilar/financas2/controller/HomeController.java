package com.ademilar.financas2.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.data.entity.Usuario;
import com.ademilar.financas2.data.repository.EmpresaRepository;
import com.ademilar.financas2.data.repository.UsuarioRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public String home(Model model, Principal principal) {   
		// Principal contém dados do usuário logado
		// também posso obter o usuário assim:
		// String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		// recupera o usuário para teste
		Usuario usuario = usuarioRepository.findByNome(principal.getName());
		
		int pagNumero = 0;
		int pagTamanho = 20;
//		Sort sort = Sort.by("fantasia").ascending();
		Sort sort = Sort.by("id").ascending();
		Pageable paginacao = PageRequest.of(pagNumero, pagTamanho, sort); 
		 
		List<Empresa> empresas = empresaRepository.findAllByUsuario(usuario.getNome(), paginacao);
		empresas.forEach(System.out::println);
		
		model.addAttribute("empresas", empresas);
		model.addAttribute("nomeUsuario", principal.getName());
		return "home";
	}
	
	@GetMapping("empresa/{id}")
	public String porStatus(@PathVariable("id") Long id, Model model) {   
		Optional<Empresa> retorno = empresaRepository.findById(id);
		Empresa empresa = retorno.isPresent() ? retorno.get() : null;
		model.addAttribute("empresa", empresa);

		return "empresa_id";
	}

	
	// manipula excessões: pode ser uma excessão geral ou específica
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
