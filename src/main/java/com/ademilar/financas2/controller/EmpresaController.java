package com.ademilar.financas2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.data.repository.Empresas;
import com.ademilar.financas2.repository.filter.EmpresaFilter;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private Empresas empresas;
	
	
	@GetMapping
	public ModelAndView pesquisar(EmpresaFilter empresaFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable) {
		ModelAndView mv = new ModelAndView("/PesquisaEmpresas");
		
		//mv.addObject("empresas", empresas.filtrar(empresaFilter, pageable));
		return mv;
	}	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
	   ModelAndView mv = new ModelAndView("CadastroEmpresa");	
	   mv.addObject(new Empresa());
	   return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Empresa empresa, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroEmpresa");
		if (errors.hasErrors()) {
			System.out.print("Encontrou erros");
			return mv;
		}
		
		empresas.save(empresa);
		
		System.out.print("Passou sem erros");
		
		mv.addObject("mensagem", "Empresa salva com sucesso");
		return mv;
	}

	
	
}
