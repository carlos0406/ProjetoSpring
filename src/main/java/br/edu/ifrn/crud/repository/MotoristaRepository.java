package br.edu.ifrn.crud.repository;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.model.Motorista;

/**
 * Interface responsavel pela comunicacao da classe Motorista com o banco de
 * dados.
 */

public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
	/**
	 * metodo com o comando hql responsavel por buscar o motorista pelo atributo
	 * nome
	 */
	@Query("select m from Motorista m where m.nome like %:nome% ")
	List<Motorista> findByName(@Param("nome") String nome);
}
