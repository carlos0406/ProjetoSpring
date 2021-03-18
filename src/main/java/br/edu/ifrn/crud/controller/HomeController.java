package br.edu.ifrn.crud.controller;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Essa classe e responsavel por gerenciar as paginas inicias do site, login,
 * login-erro e tambem pagina inicial
 */

@Controller
class HomeController {
	/**
	 * @return para indicar o diretorio do arquivo responsavel pela url
	 *         usa @GetMapping para pode indicar requisição do tipo get
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * @return para indicar o diretorio do arquivo responsavel pela url
	 *         usa @GetMapping para pode indicar requisição do tipo get
	 */
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgError", "Login ou senha incorretos, tente novamente");
		return "login";

	}

	/**
	 * @return para indicar o diretorio do arquivo responsavel pela url
	 *         usa @GetMapping para pode indicar requisição do tipo get
	 */
	@GetMapping("/")
	public String inicio() {
		return "home";
	}

}
