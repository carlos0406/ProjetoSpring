package br.edu.ifrn.crud.repository;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.crud.model.File;

/**
 * Interface responsavel pela comunicacao da classe file com o banco de dados.
 */

public interface FileRepository extends JpaRepository<File, Long> {

}
