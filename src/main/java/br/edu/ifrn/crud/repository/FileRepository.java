package br.edu.ifrn.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.crud.model.File;

public interface FileRepository extends JpaRepository<File, Long> {
	

}
