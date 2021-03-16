package br.edu.ifrn.crud.controller;

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

@Controller
public class DownloadArquivoController {
	@Autowired
	private FileRepository fileRepository;

	@GetMapping("/download/{idArquivo}")
	public ResponseEntity<?> downloadFile(@PathVariable Long idArquivo, @PathParam("salvar") String salvar) {
			File arquivoBd=fileRepository.findById(idArquivo).get();
			
			String text= (salvar==null||salvar.equals("true"))? "attachment; filename=\""+arquivoBd.getNomeArquivo()+"\"":
				"inline; filename=\""+arquivoBd.getNomeArquivo()+"\"";
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(arquivoBd.getTipoArquivo())).
				header(HttpHeaders.CONTENT_DISPOSITION, text)
				.body(new ByteArrayResource(arquivoBd.getDados()));
	}
}
