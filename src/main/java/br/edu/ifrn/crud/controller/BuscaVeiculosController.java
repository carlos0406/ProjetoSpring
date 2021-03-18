package br.edu.ifrn.crud.controller;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.model.Motorista;
import br.edu.ifrn.crud.model.Veiculo;
import br.edu.ifrn.crud.repository.MotoristaRepository;
import br.edu.ifrn.crud.repository.VeiculoRepository;

/**
 * Essa classe tem por função especificar a URL que será carregada quando
 * acessarmos a função buscar os veiculos.
 * 
 * Utilização da anotação Spring @Controller para especificar que e um
 * controlador, que essa classe irá tratar das requisições a partir do acesso a
 * essa URL.
 * 
 * Utilização do @RequestMapping para mapear as requisições web.
 *
 */
@Controller
@RequestMapping("/veiculo")
public class BuscaVeiculosController {
	
	/**
	 * @return mostra o directorio e o nome do arquivo para url.
	 * da busca de veiculos
	 * 
	 *         Utiliza @GetMapping para sinalizar uma requisição do tipo get.
	 */
	
	@GetMapping("/busca")
	public String busca() {
		return "veiculo/busca";
	}
	
	/**
	 * Implementação da interface VeiculoRepository.
	 */
	
	@Autowired
	VeiculoRepository vRepository;
	
	/**
	 * Esse classe tem como função realizar a busca dos Veiculo já cadastrados.
	 * 
	 * @param valor:              Armazena o valor que o repository vai usar para uma busca.
	 * @param model:             Envia para HTML os dados para poderem ser usados na pagina.
	 * @return mostra o directorio e o nome do arquivo para url.
	 * da busca de veiculos
	 * 
	 */

	@GetMapping("/resultado")
	public String resultado(@RequestParam(name = "valor", required = false) String valor, HttpSession session,
			ModelMap model) {

		List<Veiculo> veiculosFiltrados = new ArrayList<>();
		/**
		 * realiza uma busca no banco de dados baseado no valor do parametro valor
		 * caso seja nullo ou vazio armazena todos os veiculos na variavel 
		 * veiculosFiltrados caso seja diferente usamos o findByModelo caso seja um 
		 * textoe fyndByAno caso o valor seja do tipo numerico
		 */
		
		if (valor == null || valor.isEmpty()) {
			veiculosFiltrados = vRepository.findAll();
		} else {
			if (!isNumeric(valor)) {
				veiculosFiltrados = vRepository.findByModelo(valor);
			} else {
				veiculosFiltrados = vRepository.findByAno(Integer.valueOf(valor));
			}
		}
		/**
		 * Envia os veiculos encontrados para que possam ser usados no HTML
		 */
		model.addAttribute("veiculosBusca", veiculosFiltrados);
		
		return "veiculo/busca";

	}
	/**Essa classe serve para fazer a remoção de veiculos
	 * @param veiculoId e o id do veiculo que deve ser excluido, o id e o
	 * atributo que serve como identificador do objeto
	 * @param attr:      Redireciona os atributos especificados.
	 * @return 			 Recarrega a pagina de busca de veiculos para poder
	 * atualizar os dados
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer veiculoId, HttpSession session, RedirectAttributes attr) {
		/**
		 * Deletando o veiculo que tem um Id igual o parametro  veiculoId
		 */
		vRepository.delete(vRepository.findById(veiculoId).get());
		attr.addFlashAttribute("msgSucess", "Usuario removido com sucesso");

		return "redirect:/";
	}
	/**
	 * @param str:       String que e recebida para verificar se o que foi
	 * digitado e texto ou numerico
	 * @return 			retorna uma variavel do tipo logica que caso seja
	 * verdadeira quer dizer que o o parametro str e do tipo numerico
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
