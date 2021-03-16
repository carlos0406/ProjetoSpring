package br.edu.ifrn.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.edu.ifrn.crud.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
	@Query("select u from Usuario u where u.email like %:email% ")
	Optional<Usuario> findByUsername(@Param("email")String email);
	
}
