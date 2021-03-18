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
import br.edu.ifrn.crud.repository.MotoristaRepository;

/**
 * Essa classe tem por função especificar a URL que será carregada quando
 * acessarmos a função buscar os motoristas.
 * 
 * Utilização da anotação Spring @Controller para especificar que é um
 * controlador, que essa classe irá tratar das requisições a partir do acesso a
 * essa URL.
 * 
 * Utilização do @RequestMapping para mapear as requisições web.
 *
 */

@Controller
@RequestMapping("/motorista")
public class BuscaMotoristaController {
	/**
	 * Implementação da interface MotoristaRepository.
	 */

	@Autowired
	MotoristaRepository mRepository;

	/**
	 * @return mostra o directorio e o nome do arquivo para url. da busca de
	 *         motoristas
	 * 
	 *         Utiliza @GetMapping para sinalizar uma requisição do tipo get.
	 */

	@GetMapping("/busca")
	public String index() {
		return "motorista/busca";
	}

	/**
	 * Esse classe tem como função realizar a busca dos Motoristas já cadastrados.
	 * 
	 * @param name:  Armazena o valor que o repository vai usar para uma busca.
	 * @param model: Envia para HTML os dados para poderem ser usados na pagina.
	 * @return mostra o directorio e o nome do arquivo para url. da busca de
	 *         motoristas
	 * 
	 */

	@GetMapping("/resultado")
	public String results(@RequestParam(name = "nome", required = false) String name, ModelMap model) {

		List<Motorista> motoristasFiltrados = new ArrayList<>();
		/**
		 * realiza uma busca no banco de dados baseado no valor do parametro nome caso
		 * seja nullo ou vazio armazena todos os motoristas na variavel
		 * motoristasFiltrados caso seja diferente usamos o findByName para filtar os
		 * usuários
		 */
		if (name == null || name.isEmpty()) {
			motoristasFiltrados = mRepository.findAll();

		} else {
			if (name != null) {
				motoristasFiltrados = mRepository.findByName(name);
			}
		}
		/**
		 * Envia os motoristas encontrados para que possam ser usados no HTML
		 */
		model.addAttribute("motoristasFiltrados", motoristasFiltrados);

		return "motorista/busca";

	}

	/**
	 * Essa classe serve para fazer a remoção de Motoristas
	 * 
	 * @param motoristaId é o id do motorista que deve ser excluido, o id é o
	 *                    atributo que serve como identificador do objeto
	 * @param attr:       Redireciona os atributos especificados.
	 * @return Recarrega a pagina de busca de motoristas para poder atualizar os
	 *         dados
	 */
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer motoristaId, HttpSession session, RedirectAttributes attr) {
		/**
		 * Deletando o motorista que tem um Id igual o parametro motorista id
		 */
		mRepository.delete(mRepository.findById(motoristaId).get());
		attr.addFlashAttribute("msgSucess", "Usuario removido com sucesso");

		return "redirect:/motorista/busca";
	}

}
