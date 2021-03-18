package br.edu.ifrn.crud.controller;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.crud.model.File;
import br.edu.ifrn.crud.repository.FileRepository;

/**
 * Essa classe está responsável pela implementação do download de todos os
 * arquivos no Banco de Dados.
 */
@Controller
public class DownloadArquivoController {
	/**
	 * Implementação da interface FileRepository.
	 */
	@Autowired
	private FileRepository fileRepository;

	/**
	 * Essa classe tem a função de fazer o download da imagem indicada peloId
	 * 
	 * @param idArquivo: Permite acessar os atributos da entidade Arquivo.
	 * @param salvar:    Permite salvar a imagem desejada.
	 * @return Retorna o arquivo especificado.
	 */
	@GetMapping("/download/{idArquivo}")
	public ResponseEntity<?> downloadFile(@PathVariable Long idArquivo, @PathParam("salvar") String salvar) {
		/**
		 * Carregando arquivo do Banco de Dados.
		 */
		File arquivoBd = fileRepository.findById(idArquivo).get();

		String text = (salvar == null || salvar.equals("true"))
				? "attachment; filename=\"" + arquivoBd.getNomeArquivo() + "\""
				: "inline; filename=\"" + arquivoBd.getNomeArquivo() + "\"";
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(arquivoBd.getTipoArquivo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, text).body(new ByteArrayResource(arquivoBd.getDados()));
	}
}
