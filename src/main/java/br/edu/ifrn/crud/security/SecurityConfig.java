package br.edu.ifrn.crud.security;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrn.crud.model.Usuario;
import br.edu.ifrn.crud.service.UsuarioService;

/**
 * Classe responsavel por gerenciamentos de permisão sobre URls permitindo ou
 * negando acesso a metodos e paginas
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Implementação da classe UsuarioService, para especificar que os usuários que
	 * consiguiram logar são usuarios cadastrados no banco de dados.
	 */
	@Autowired
	private UsuarioService service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * as URL com os nomes especificados estão permitidos a qualquer usuário
		 * acessar.
		 */
		http.authorizeRequests().antMatchers("/css/**", "/images/**", "/js/**").permitAll().antMatchers("/publico/**")
				.permitAll()
				/**
				 * as URLs listadas fazem com que apenas usuarios ADMIN possam usar essas urls.
				 */
				.antMatchers("/motorista/criar_motorista", "/**/salvar/", "/**/edicao/**", "/**/remove/**",
						"/veiculo/criar_veiculo")
				.hasAuthority(Usuario.ADMIN)
				/**
				 * Codigo responsavel para redirecionar os usuarios do sistema para pagina de
				 * login.
				 */
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/", true)
				.failureUrl("/login-error").permitAll().and().logout().logoutSuccessUrl("/").and().rememberMe();

	}

	/**
	 * Permite a criptografia usando o BCrypt.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

}
