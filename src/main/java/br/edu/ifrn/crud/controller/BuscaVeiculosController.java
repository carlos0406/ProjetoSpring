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
import br.edu.ifrn.crud.model.Veiculo;
import br.edu.ifrn.crud.repository.MotoristaRepository;
import br.edu.ifrn.crud.repository.VeiculoRepository;

@Controller
@RequestMapping("/veiculo")
public class BuscaVeiculosController {
	
	@GetMapping("/busca")
	public String busca() {
		return "veiculo/busca";
	}
	@Autowired
	VeiculoRepository vRepository;
	
	@GetMapping("/resultado")
	public String resultado(@RequestParam(name = "valor", required = false)
	String valor,HttpSession session, ModelMap model) {

		
		List<Veiculo> veiculosFiltrados = new ArrayList<>();
		
		if (valor == null || valor.isEmpty()) {
			veiculosFiltrados =vRepository.findAll() ;
		}else {
			if(!isNumeric(valor)) {
			veiculosFiltrados=vRepository.findByModelo(valor);
			}else {
				veiculosFiltrados=vRepository.findByAno(Integer.valueOf(valor));
			}
		}
		

		model.addAttribute("veiculosBusca", veiculosFiltrados);

		return "veiculo/busca";

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer veiculoId,HttpSession session,
			RedirectAttributes attr) {
		
		vRepository.delete(vRepository.findById(veiculoId).get());
		attr.addFlashAttribute("msgSucess","Usuario removido com sucesso");
		
		return "redirect:/";
	}
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
