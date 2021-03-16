package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
	@Query("select v from Veiculo v where v.ano = :ano")
	List<Veiculo> findByAno(@Param("ano")int ano);
	
	@Query("select v from Veiculo v where v.modelo like %:modelo%")
	List<Veiculo> findByModelo(@Param("modelo")String modelo);
	

}
