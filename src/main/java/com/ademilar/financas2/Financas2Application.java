package com.ademilar.financas2;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import com.ademilar.financas2.data.service.CrudEmpresaService;
import com.ademilar.financas2.data.service.RelatorioDinamicoEmpresaService;

@SpringBootApplication
public class Financas2Application implements CommandLineRunner {

	private final CrudEmpresaService empresaService;
	private final RelatorioDinamicoEmpresaService relatorioDinamicoEmpresaService;
	

	// construtor (para teste)
	public Financas2Application(CrudEmpresaService empresaService, RelatorioDinamicoEmpresaService relatorioDinamicoEmpresaService) {
		this.empresaService = empresaService;
		this.relatorioDinamicoEmpresaService = relatorioDinamicoEmpresaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Financas2Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	// método da interface CommandLineRunner
	// executado na finalização do método main()
	// podemos usar para testar os repositórios, sem necessitar do front end
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Método run() executou");

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("\n\n======= Sistema Financeiro Ademilar Consórcios =======");
			System.out.println("1 = Cadastrar empresa dados padrões");
			System.out.println("2 = Cadastrar empresa novos dados");
			System.out.println("3 = Cadastrar 100 empresas aleatórias");			
			System.out.println("4 = Listar todas empresas");
			System.out.println("5 = Listar todas empresas usando paginação (20/pagina) e ordenação ascendente por nome fantasia");
			System.out.println("6 = Buscar por razão social (ordenado por 'usuario_nome') descendente");
			System.out.println("7 = Buscar por nome fantasia");
			System.out.println("8 = Buscar por CNPJ");
			System.out.println("9 = Buscar por cidade");
			System.out.println("10 = Buscar por bairro");
			System.out.println("1' = Buscar por cep");
			System.out.println("11 = Buscar por UF");
			System.out.println("13 = Listar Empresa Projeção");
			System.out.println("14 = Relatório Dinâmico Empresa (usando Specification)");
			System.out.println("15 = Chamar Procedure: get_total_empresas");
			System.out.println("16 = Incluir tipoConta na Empresa");
			
			System.out.println("Qual opção?");
			int opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
				empresaService.cadastroPadrao();
				break;

			case 2:
				empresaService .cadastroNovo();
				break;
				
			case 3:
				empresaService .cadastrar100Empresas();
				break;
				
			case 4:
				empresaService.listarTodas();
				break;
				
			case 5:
				empresaService.listarTodasPaginacao();
				break;
				
				
			case 6:
				empresaService.buscarPorRazaoSocial();
				break;
				
			case 7:
				empresaService.buscarPorFantasia();
				break;
				
			case 8:
				empresaService.buscarPorCNPJ();
				break;
				
			case 9:
				empresaService.buscarPorCidade();
				break;
				
			case 10:
				empresaService.buscarPorBairro();
				break;
				
			case 11:
				empresaService.buscarPorCep();
				break;
				
			case 12:
				empresaService.buscarPorUF();
				break;
				
			case 13:
				empresaService.listarEmpresaProjecao();
				break;
				
			case 14:
				relatorioDinamicoEmpresaService.teste();
				break;
				
			case 15:
				empresaService.totalEmpresas();
				break;

			case 16:
				empresaService.cadastroNovaConta();
				break;
				
			default:
				System.out.println("Opção inválida!");
				break;
			}
		}

	}

}
