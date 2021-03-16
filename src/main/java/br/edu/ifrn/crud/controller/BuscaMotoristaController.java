package br.edu.ifrn.crud.controller;

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

@Controller
@RequestMapping("/motorista")
public class BuscaMotoristaController {
	@Autowired
	MotoristaRepository mRepository;

	@GetMapping("/busca")
	public String index() {
		return "motorista/busca";
	}

	@GetMapping("/resultado")
	public String results(@RequestParam(name = "nome", required = false) String name,
			@RequestParam(name = "allData", required = false) Boolean allData, HttpSession session, ModelMap model) {
		

		List<Motorista> motoristasFiltrados = new ArrayList<>();

		if (name == null || name.isEmpty()) {
			motoristasFiltrados = mRepository.findAll();
			
		} else {
			if (name!= null) {
				motoristasFiltrados =mRepository.findByName(name) ;
			}
		}

		model.addAttribute("motoristasFiltrados", motoristasFiltrados);
		
		return "motorista/busca";

	}
	
	
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer motoristaId,HttpSession session,
			RedirectAttributes attr) {
		
			mRepository.delete(mRepository.findById(motoristaId).get());
			attr.addFlashAttribute("msgSucess","Usuario removido com sucesso");
			
		
			
		
		
		return "redirect:/motorista/busca";
	}
	
	

}
