package br.edu.ifrn.crud.service;

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
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=(repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado")));
		
		System.out.println(usuario.getEmail() + usuario.getPassword());
		
		return new User(
				usuario.getEmail(),
				usuario.getPassword(),
				AuthorityUtils.createAuthorityList(usuario.getPerfil())
				);
		
}}
