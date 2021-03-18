package br.edu.ifrn.crud.controller;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.model.File;
import br.edu.ifrn.crud.model.Motorista;
import br.edu.ifrn.crud.model.Veiculo;
import br.edu.ifrn.crud.repository.FileRepository;
import br.edu.ifrn.crud.repository.MotoristaRepository;
import br.edu.ifrn.crud.repository.VeiculoRepository;

/**
 * Essa classe tem por função especificar a URL que será carregada quando
 * acessarmos a função de criar Motoristas.
 * 
 * Utilização da anotação Spring @Controller para especificar que e um
 * controlador, que essa classe irá tratar das requisições a partir do acesso a
 * essa URL.
 * 
 * Utilização do @RequestMapping para mapear as requisições web.
 *
 */

@Controller
@RequestMapping("/motorista")
class CriarMotoristaController {
	/**
	 * Implementação da interface MotoristaRepository.
	 */
	@Autowired
	MotoristaRepository mRepository;
	/**
	 * Implementação da interface VeiculoRepository.
	 */
	@Autowired
	VeiculoRepository vRepository;
	/**
	 * Implementação da interface FileRepository.
	 */
	@Autowired
	FileRepository fRepository;

	/**
	 * @param model: Envia para HTML os dados para poderem ser usados na pagina
	 * 
	 * @return mostra o directorio e o nome do arquivo para url. da busca de
	 *         veiculos
	 * 
	 *         Utiliza @GetMapping para sinalizar uma requisição do tipo get.
	 */

	@GetMapping("/criar_motorista")
	public String criarMotorista(ModelMap model) {
		model.addAttribute("motorista", new Motorista());
		model.addAttribute("veiculo", new Veiculo());
		return "motorista/criar_motorista";
	}

	/**
	 * Esse classe tem como função realizar a inserção dos Motoristas no banco de
	 * dados.
	 * 
	 * @param motorista:   Armazena um objeto do tipo Motorista e todos os seus
	 *                     dados.
	 * @param model:       Envia para HTML os dados para poderem ser usados na
	 *                     pagina.
	 * @param documentoCad Recebe um arquivo para poder armazenar no banco de dados
	 * @param fotoCad      Recebe um arquivo para poder armazenar no banco de dados
	 * @return mostra o directorio e o nome do arquivo para url. da busca de
	 *         motoristas
	 * 
	 *         Ultiliza @PostMapping para indicar requisição do tipo post
	 *         Ultiliza @Transactional para indicar que o metodo vai ter acesso ao
	 *         banco de dados
	 * 
	 */
	@PostMapping("/salvar")
	@Transactional
	public String save(Motorista motorista, HttpSession session, RedirectAttributes attr,
			@RequestParam("documentoCad") MultipartFile documentoCad, @RequestParam("fotoCad") MultipartFile fotoCad) {

		/**
		 * Normalizando os nomes dos seus arquivos e relacionando com o objeto
		 * motorista.
		 */
		try {
			/**
			 * Normalizando os nomes dos arquivos
			 */
			String nomeDocumento = StringUtils.cleanPath(documentoCad.getOriginalFilename());
			String nomeFoto = StringUtils.cleanPath(fotoCad.getOriginalFilename());
			/**
			 * criando objetos File para serem salvos no banco de dados
			 */
			File arquivoBD = new File(null, nomeDocumento, documentoCad.getContentType(), documentoCad.getBytes());

			File fotoBD = new File(null, nomeFoto, fotoCad.getContentType(), fotoCad.getBytes());
			/**
			 * Salvando objetos no banco de dados.
			 */
			fRepository.save(arquivoBD);
			fRepository.save(fotoBD);

			if (motorista.getDocumento() != null && motorista.getDocumento().getId() != null
					&& motorista.getDocumento().getId() > 0) {
				fRepository.delete(motorista.getDocumento());

			}
			if (motorista.getFoto() != null && motorista.getFoto().getId() != null && motorista.getFoto().getId() > 0) {
				fRepository.delete(motorista.getDocumento());

			}
			/**
			 * Relacionando os documentos com o motorista
			 */
			motorista.setDocumento(arquivoBD);
			motorista.setFoto(fotoBD);
			mRepository.save(motorista);
			attr.addFlashAttribute("msgSucess", "Cadastro de motorista realizado com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/motorista/criar_motorista";
	}

	/**
	 * Essa classe tem como função a edição de motoristas cadastrados
	 * 
	 * @param motoristaId é o id do motorista que deve ser excluido, o id é o
	 *                    atributo que serve como identificador do objeto
	 * @param model:      Envia os objetos para poderem ser utilizados no HTML.
	 * @return Recarrega a pagina de busca de motoristas para poder atualizar os
	 *         dados
	 */
	@GetMapping("/edicao/{id}")
	public String edition(@PathVariable("id") Integer motoristaId, ModelMap model) {
		/**
		 * Envia o Motorista encontrado para que possam ser usados no HTML
		 */
		Motorista m = mRepository.findById(motoristaId).get();
		model.addAttribute("motorista", m);
		return "/motorista/criar_motorista";
	}

	@ModelAttribute("veiculos")
	public List<Veiculo> getVeiculos(HttpSession session) {
		return vRepository.findAll();

	}

}
