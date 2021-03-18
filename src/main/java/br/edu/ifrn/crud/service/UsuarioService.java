package br.edu.ifrn.crud.service;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.crud.model.Usuario;
import br.edu.ifrn.crud.repository.UserRepository;

@Service
public class UsuarioService implements UserDetailsService {
	/**
	 * Imprementacao de interface usuario repository
	 */
	@Autowired
	private UserRepository repository;

	/**
	 * Realiza o login baseado nos atributos especificados.
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/**
		 * Realiza uma busca no banco de daods pelo email do usuario cadastrado.
		 * 
		 * Mostra uma mensagem para o usuário caso o usuario não seja encontrado.
		 */
		Usuario usuario = (repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));

		/**
		 * Cria uma lista de autoridades para os perfis de Usuario, possibilitando que,
		 * com seu email e senha, possam realizar login.
		 */

		return new User(usuario.getEmail(), usuario.getPassword(),
				AuthorityUtils.createAuthorityList(usuario.getPerfil()));

	}
}
