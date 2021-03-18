package br.edu.ifrn.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import br.edu.ifrn.crud.repository.VeiculoRepository;

@Controller
@RequestMapping("/veiculo")
public class CriarVeiculoController {
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
	@GetMapping("/criar_veiculo")
	public String criarVeiculo(ModelMap model) {
		model.addAttribute("veiculo", new Veiculo());
		return "veiculo/criar_veiculo";
	}

	/**
	 * Esse classe tem como função realizar a inserção dos Motoristas no banco de
	 * dados.
	 * 
	 * @param Veiculo: Armazena um objeto do tipo veiculo e todos os seus dados.
	 * @param model:   Envia para HTML os dados para poderem ser usados na pagina.
	 * @param arquivo  Recebe um arquivo para poder armazenar no banco de dados
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
	public String save(Veiculo veiculo, HttpSession session, RedirectAttributes attr,
			@RequestParam("file") MultipartFile arquivo) {
		/**
		 * Normalizando o nome do seu arquivo e relacionando com o objeto veiculo.
		 */
		try {
			/**
			 * Normalizando o nome do arquivo
			 */
			String nomeDocumento = StringUtils.cleanPath(arquivo.getOriginalFilename());
			/**
			 * criando um objeto File para ser salvo no banco de dados
			 */
			File arquivoBD = new File(null, nomeDocumento, arquivo.getContentType(), arquivo.getBytes());
			/**
			 * Salvando objeto no banco de dados.
			 */
			fRepository.save(arquivoBD);

			if (veiculo.getDocumento() != null && veiculo.getDocumento().getId() != null
					&& veiculo.getDocumento().getId() > 0) {
				fRepository.delete(veiculo.getDocumento());

			}
			/**
			 * Relacionando veiculo com seu documento
			 */
			veiculo.setDocumento(arquivoBD);
			vRepository.save(veiculo);
			attr.addFlashAttribute("msgSucess", "Cadastro de veiculo realizado com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/veiculo/criar_veiculo";
	}

	/**
	 * Essa classe tem como função a edição de motoristas cadastrados
	 * 
	 * @param veiculoId é o id do motorista que deve ser excluido, o id é o atributo
	 *                  que serve como identificador do objeto
	 * @param model:    Envia os objetos para poderem ser utilizados no HTML.
	 * @return Recarrega a pagina de busca de motoristas para poder atualizar os
	 *         dados
	 */
	@GetMapping("/edicao/{id}")
	public String edition(@PathVariable("id") Integer veiculoId, HttpSession session, ModelMap model) {
		/**
		 * Envia o veiculo encontrados para que possam ser usados no HTML
		 */
		Veiculo v = vRepository.findById(veiculoId).get();

		model.addAttribute("veiculo", v);
		return "/veiculo/criar_veiculo";

	}

}
