package com.ademilar.financas2.data.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ademilar.financas2.data.entity.Empresa;
import com.ademilar.financas2.data.entity.Endereco;
import com.ademilar.financas2.data.entity.FoneEmpresa;
import com.ademilar.financas2.data.entity.PlanoConta;
import com.ademilar.financas2.data.entity.Usuario;
import com.ademilar.financas2.data.model.TipoConta;
import com.ademilar.financas2.data.model.TipoFone;
import com.ademilar.financas2.data.model.UF;
import com.ademilar.financas2.data.projection.EmpresaProjection;
import com.ademilar.financas2.data.repository.EmpresaRepository;
import com.ademilar.financas2.data.repository.PlanoContaRepository;
import com.ademilar.financas2.data.repository.UsuarioRepository;

// Classe para teste do CRUD
@Service
public class CrudEmpresaService {

	private final EmpresaRepository empresaRepository;
	private final UsuarioRepository usuarioRepository;
	private final PlanoContaRepository planoContaRepository;	

	public CrudEmpresaService(EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository, PlanoContaRepository planoContaRepository) {
		this.empresaRepository = empresaRepository;
		this.usuarioRepository = usuarioRepository;
		this.planoContaRepository = planoContaRepository;		
	}

	public void cadastroPadrao() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Thor Fábrica de Martelos EIRELI");
		empresa.setFantasia("Odin Martelos");
		empresa.setIE("ISENTO");
		empresa.setCNPJ("12345678-00");

		Endereco endereco = new Endereco();
		endereco.setRua("XV de Novembro");
		endereco.setNumero("123");
		endereco.setBairro("Centro");
		endereco.setCep("84010-000");
		endereco.setCidade("Ponta Grossa");
		endereco.setUf(UF.PR);
		endereco.setComplemento("AP 99");

		FoneEmpresa fixo = new FoneEmpresa();
		fixo.setEmpresa(empresa);
		fixo.setNumero("(42) 3028-0449");
		fixo.setRamal("123");
		fixo.setTipo(TipoFone.FIXO);

		FoneEmpresa whataspp = new FoneEmpresa();
		whataspp.setEmpresa(empresa);
		whataspp.setNumero("(42) 98402-1234");
		whataspp.setTipo(TipoFone.WHATASPP);

		empresa.setEndereco(endereco);
		System.out.println("Telefones: " + empresa.getFones());
		empresa.getFones().add(fixo);
		empresa.getFones().add(whataspp);

		empresaRepository.save(empresa);
		System.out.println("Empresa salva");
	}

	public void cadastroNovo() {
		Scanner scanner = new Scanner(System.in);
		Empresa empresa = new Empresa();

		System.out.println("Razão social:");
		empresa.setRazaoSocial(scanner.nextLine());

		System.out.println("Nome fantasia:");
		empresa.setFantasia(scanner.nextLine());

		System.out.println("Inscrição Estadual:");
		empresa.setIE(scanner.nextLine());

		System.out.println("CNPJ: ");
		empresa.setCNPJ(scanner.nextLine());

		Endereco endereco = new Endereco();

		System.out.println("Rua: ");
		endereco.setRua(scanner.nextLine());

		System.out.println("Número: ");
		endereco.setNumero(scanner.nextLine());

		System.out.println("Bairro:");
		endereco.setBairro(scanner.nextLine());

		System.out.println("Cep:");
		endereco.setCep(scanner.nextLine());

		System.out.println("Cidade:");
		endereco.setCidade(scanner.nextLine());

		System.out.println("UF: ");
		endereco.setUf(UF.valueOf(scanner.nextLine().toUpperCase()));

		System.out.println("Complemento:");
		endereco.setComplemento(scanner.nextLine());

		FoneEmpresa fone = new FoneEmpresa();

		fone.setEmpresa(empresa);

		System.out.println("Número telefone: ");
		fone.setNumero(scanner.nextLine());

		System.out.println("Ramal:");
		fone.setRamal(scanner.nextLine());

		System.out.println("Tipo (FIXO/CELULAR/WHATSAPP): ");
		fone.setTipo(TipoFone.valueOf(scanner.nextLine()));

		empresa.setEndereco(endereco);
		empresa.getFones().add(fone);
		empresaRepository.save(empresa);
		System.out.println("Empresa salva!");
	}
	
	public void cadastroNovaConta() {
		Scanner scanner = new Scanner(System.in);
		
		Empresa empresa = new Empresa();
		PlanoConta planoConta = new PlanoConta();

		System.out.println("Id Empresa:");
		Long vcod_empresa = Long.parseLong(scanner.nextLine());
		Optional<Empresa> optional = empresaRepository.findById(vcod_empresa);
		empresa = optional.get();

		planoConta.setEmpresa(empresa);
		
		System.out.println("Descrição:");
		planoConta.setDescricao(scanner.nextLine());

		System.out.println("Num. Conta:");
		planoConta.setNumConta(scanner.nextLine());

		System.out.println("Tipo Conta:  SINTETICA/ANALITICA");
		planoConta.setTipoConta(TipoConta.valueOf(scanner.nextLine()));

		empresa.getContas().add(planoConta);
		empresaRepository.save(empresa);
//		planoConta.save(planoConta);
		System.out.println("Conta salva!");
	}
	

	public void cadastrar100Empresas() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		System.out.println("\n\n*** Escolha um dos usuários abaixo para referenciar na empresa:");
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Qual nome (deve ser idêntico) ? ");
		String nome = teclado.next();
		
		Usuario usuario = usuarioRepository.findByNome(nome);
		
		for (int i = 1; i <= 100; i++) {
			Empresa empresa = new Empresa();
			empresa.setFantasia(randomTexto(7, false));
			empresa.setRazaoSocial(randomTexto(7, true) + " LTDA");
			empresa.setCNPJ(randomCNPJ());
			empresa.setEmail(randomTexto(5, false) + "@gmail.com");
			empresa.setIE("Isento");
			empresa.setUsuario(usuario);

			Endereco endereco = new Endereco();
			endereco.setRua("Santana");
			endereco.setNumero("820");
			endereco.setBairro("Centro");
			endereco.setCep("84010-320");
			endereco.setCidade("Ponta Grossa");
			endereco.setUf(UF.PR);
			empresa.setEndereco(endereco);

			FoneEmpresa fone1 = new FoneEmpresa();
			fone1.setEmpresa(empresa);
			fone1.setNumero("(42) 3028-0449");
			fone1.setTipo(TipoFone.FIXO);

			FoneEmpresa fone2 = new FoneEmpresa();
			fone2.setEmpresa(empresa);
			fone2.setNumero("(42)99955-4444");
			fone2.setTipo(TipoFone.WHATASPP);

			empresa.getFones().add(fone1);
			empresa.getFones().add(fone2);

			empresaRepository.save(empresa);
			System.out.printf("\nEmpresa %d/100 salva", i);
		}
	}

	public void listarTodas() {
		System.out.println("\n\n=== Listando Todas as Empresas ====");
		List<Empresa> todas = empresaRepository.findAll();
		todas.forEach(System.out::println);
	}

	public void listarTodasPaginacao() {		

		// paginação e ordenação por id ascendente
		Pageable paginacao = PageRequest.of(0, 20, Sort.by("id").ascending());

		Page<Empresa> pagina = empresaRepository.findAll(paginacao);
		System.out.println("\n\n>>Total de páginas: " + pagina.getTotalPages()); // 0..n
		System.out.println(">>Página atual: " + pagina.getNumber());
		System.out.println(">>Itens por página: " + pagina.getSize()); // 0..n
		System.out.println(">>Total de itens da consulta: " + pagina.getTotalElements());

		Scanner scanner = new Scanner(System.in);
		String escolha = "";

		do {
			
			System.out.println("\n\n======== Menu de Paginação ========");
			System.out.println("MOSTRAR = mostra conteúdo da página atual");
			System.out.println("PROX = vai para próxima página");
			System.out.println("ANT = vai para página anterior");
			System.out.println("PRI = vai para primera página");
			System.out.println("ULT = vai para ultima página");
			System.out.println("CONV = converte página atual em lista");
			System.out.println("FIM = sai do menu");
			System.out.println("Qual opção? ");
			escolha = scanner.next().toUpperCase();
			
			if (escolha.equals("FIM")) {
				System.out.println("\nSaiu do menu!");
				break;
			}
			
			switch (escolha) {
			
			case "MOSTRAR":
				System.out.println("\n\n-------- Exibindo página: " + pagina.getNumber());
				pagina.forEach(System.out::println);
				break;

			case "PROX":
				if (pagina.hasNext()) {
					pagina = empresaRepository.findAll(pagina.nextPageable());
					System.out.println("\n\n*** Foi para página: " + pagina.getNumber());
				} else {
					System.out.println("\n\n*** Não há próxima página");
				}
				
				break;
				
			case "ANT":
				if (pagina.hasPrevious()) {
					pagina = empresaRepository.findAll(pagina.previousPageable());
					System.out.println("\n\n*** Foi para página: " + pagina.getNumber());
				} else {
					System.out.println("\n\n*** Não há página anterior");
				}
				
				break;
				
			case "PRI":				
				pagina = empresaRepository.findAll(paginacao.first());				
				System.out.println("\n\n*** Foi para página: " + pagina.getNumber());
				break;
				
			case "ULT":
				paginacao = PageRequest.of(pagina.getTotalPages() - 1, 20, Sort.by("id").ascending());
				pagina = empresaRepository.findAll(paginacao);				
				System.out.println("\n\n*** Foi para página: " + pagina.getNumber());				
				break;
				
			case "CONV":
				List<Empresa> lista = pagina.getContent();
				System.out.println("List<Empresa> lista = pagina.getContent();");
				break;
				
			default:
				System.out.println("\n*** Opção inválida! ***");
				break;
			}
			
		} while (true);

		
	}

	public void buscarPorRazaoSocial() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a razão social");
		String razao = scanner.nextLine();

		Sort sort = Sort.by("usuario_nome").descending();

		List<Empresa> lista = empresaRepository.findByRazaoSocialContaining(razao, sort);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Nenhuma empresa não encontrada!");
	}

	public void buscarPorFantasia() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome fantasia");
		String fantasia = scanner.nextLine();

		List<Empresa> lista = empresaRepository.findByFantasiaContaining(fantasia);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Nenhuma empresa encontrada!");
	}

	public void buscarPorCNPJ() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o CNPJ");
		String cnpj = scanner.nextLine();

		List<Empresa> lista = empresaRepository.findByCNPJContaining(cnpj);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Empresa não encontrada!");
	}

	public void buscarPorCidade() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a cidade");
		String cnpj = scanner.nextLine();

		List<Empresa> lista = empresaRepository.findByEndereco_CidadeContaining(cnpj);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Empresa não encontrada!");
	}

	public void buscarPorBairro() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o bairro");
		String bairro = scanner.nextLine();

		List<Empresa> lista = empresaRepository.findByEndereco_BairroContaining(bairro);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Empresa não encontrada!");
	}

	public void buscarPorCep() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o CEP");
		String cep = scanner.nextLine();

		List<Empresa> lista = empresaRepository.findByEndereco_CepContaining(cep);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Empresa não encontrada!");
	}

	public void buscarPorUF() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite UF");
		UF uf = UF.valueOf(scanner.nextLine().toUpperCase());

		List<Empresa> lista = empresaRepository.findByEndereco_uf(uf);
		if (!lista.isEmpty())
			lista.forEach(System.out::println);
		else
			System.out.println("Empresa não encontrada!");

	}

	public void listarEmpresaProjecao() {
		List<EmpresaProjection> lista = empresaRepository.findEmpresaProjecao();

		if (!lista.isEmpty())
			lista.forEach(e -> System.out.println("Empresa: Id: " + e.getId() + " | Razão Social: "
					+ e.getRazao_Social() + " | CNPJ: " + e.getCNPJ()));
		else
			System.out.println("Empresa não encontrada!");
	}

	public void totalEmpresas() {
		int total = empresaRepository.getTotalEmpresas();
		System.out.println("Procedure retornou: " + total);
	}

	public String randomCNPJ() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			char numero = (char) (48 + (Math.random() * 10));
			builder.append(numero);
		}

		builder.append("-00");
		return builder.toString();
	}


	public String randomTexto(int tamanho, boolean upper) {
		StringBuilder builder = new StringBuilder();
		int base = upper ? 65 : 97;
		for (int i = 0; i < tamanho; i++) {
			char letra = (char) (base + (Math.random() * 26));
			builder.append(letra);
		}

		return builder.toString();
	}
}
