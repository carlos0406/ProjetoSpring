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
	@Autowired
	VeiculoRepository vRepository;
	
	@Autowired
	FileRepository fRepository;
	
	@GetMapping("/criar_veiculo")
	public String criarVeiculo(ModelMap model) {
		model.addAttribute("veiculo", new Veiculo());
		return "veiculo/criar_veiculo";
	}

	

	@PostMapping("/salvar")
	@Transactional	
	public String save(Veiculo veiculo, HttpSession session, RedirectAttributes attr, @RequestParam("file") MultipartFile arquivo) {
		try {
			
				String nomeDocumento=
						StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				File arquivoBD=new File(
						null,nomeDocumento,arquivo.getContentType(),arquivo.getBytes());
				
			fRepository.save(arquivoBD);
			
			if(veiculo.getDocumento()!=null && veiculo.getDocumento().getId()!=null && veiculo.getDocumento().getId()>0) {
				fRepository.delete(veiculo.getDocumento());
				
			}
			veiculo.setDocumento(arquivoBD)	;
			vRepository.save(veiculo);
			attr.addFlashAttribute("msgSucess","Cadastro de veiculo realizado com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "redirect:/veiculo/criar_veiculo";
	}

	@GetMapping("/edicao/{id}")
	public String edition(@PathVariable("id") Integer veiculoId, HttpSession session, ModelMap model) {
		Veiculo v = vRepository.findById(veiculoId).get();

		model.addAttribute("veiculo", v);
		return "/veiculo/criar_veiculo";

	}
	
	//public List<String> validarDados()
}
