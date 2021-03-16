package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.model.Motorista;



public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
	@Query("select m from Motorista m where m.nome like %:nome% ")
	List<Motorista> findByName(@Param("nome")String nome);
}
