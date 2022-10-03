package com.ademilar.financas2.repository.helper.empresa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.repository.filter.EmpresaFilter;

public class EmpresasImpl implements EmpresasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Empresa> filtrar(EmpresaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Empresa.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome_Empresa())) {
				criteria.add(Restrictions.ilike("Nome_Empresa", filtro.getNome_Empresa(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getFantasia_Empresa())) {
				criteria.add(Restrictions.ilike("Fantasia_Empresa", filtro.getFantasia_Empresa(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getCNPJ_Empresa())) {
				criteria.add(Restrictions.ilike("CNPJ_Empresa", filtro.getCNPJ_Empresa(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getIE_Empresa())) {
				criteria.add(Restrictions.ilike("IE_Empresa", filtro.getIE_Empresa(), MatchMode.ANYWHERE));
			}
		}
		
		return criteria.list();
	}
}
