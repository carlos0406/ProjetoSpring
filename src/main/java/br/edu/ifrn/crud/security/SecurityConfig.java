package br.edu.ifrn.crud.security;

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
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http.authorizeRequests().
				antMatchers("/css/**","/images/**","/js/**").permitAll()
				.antMatchers("/publico/**").permitAll()
				.antMatchers("/motorista/criar_motorista","/**/salvar/","/**/edicao/**","/**/remove/**",
					"/veiculo/criar_veiculo").hasAuthority(Usuario.ADMIN) 
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/",true).
				failureUrl("/login-error")
				.permitAll()
				.and().logout().logoutSuccessUrl("/")
				.and()
					.rememberMe();
				

	}
	
	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

}
