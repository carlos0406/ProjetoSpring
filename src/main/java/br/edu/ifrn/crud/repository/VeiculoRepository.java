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

import br.edu.ifrn.crud.model.Veiculo;

/**
 * Interface responsavel pela comunicacao da classe Veiculo com o banco de
 * dados.
 */

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
	/**
	 * metodo com o comando hql responsavel por buscar o veiculo pelo atributo ano
	 */
	@Query("select v from Veiculo v where v.ano = :ano")
	List<Veiculo> findByAno(@Param("ano") int ano);

	/**
	 * metodo com o comando hql responsavel por buscar o motorista pelo atributo
	 * modelo
	 */
	@Query("select v from Veiculo v where v.modelo like %:modelo%")
	List<Veiculo> findByModelo(@Param("modelo") String modelo);

}
