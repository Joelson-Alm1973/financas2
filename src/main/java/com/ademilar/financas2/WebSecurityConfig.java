package com.ademilar.financas2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			// páginas de acesso público
			//.antMatchers("/home/**")      // /home/empresa/1        
			//	.permitAll()
			// todas as outras devem ser autenticadas
			.anyRequest()
				.authenticated()				 
			.and()
			.formLogin(form -> form
					.loginPage("/login")   // é a pagina de login (controller)
					.defaultSuccessUrl("/home", true)
					.permitAll()
			)
			.logout(logout -> logout.logoutUrl("/logout"))
			
			// desabilita a segurança de requisição
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserDetails user =
				 User.builder()
					.username("joelson")
					.password(encoder.encode("123"))
					.roles("USER")
					.build();
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
		
		
		//	.withUser(user);
	}
	
	/* Usuário apenas em memória (não usa o banco de dados)
	 * Não precisa do método configure
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("joelson")
				.password("ademilar")
				.roles("ADM")
				.build();

		return new InMemoryUserDetailsManager(user);
	}	
	*/
}
