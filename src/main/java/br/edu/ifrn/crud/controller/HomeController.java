package br.edu.ifrn.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
class HomeController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgError","Login ou senha incorretos, tente novamente");
		return "login";
		
	}
	
	@GetMapping("/")
	public String inicio() {
		return "home";
	}

}
