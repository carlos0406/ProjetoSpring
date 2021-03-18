package br.edu.ifrn.crud.repository;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.model.Usuario;

/**
 * Interface responsavel pela comunicacao da classe User com o banco de dados.
 */
public interface UserRepository extends JpaRepository<Usuario, Integer> {
	/**
	 * metodo com o comando hql responsavel por buscar o Usuario pelo atributo email
	 */
	@Query("select u from Usuario u where u.email like %:email% ")
	Optional<Usuario> findByUsername(@Param("email") String email);

}
