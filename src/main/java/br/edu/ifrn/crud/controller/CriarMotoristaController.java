package br.edu.ifrn.crud.controller;

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

@Controller 

@RequestMapping("/motorista")
class CriarMotoristaController {
	@Autowired
	MotoristaRepository mRepository;
	@Autowired
	VeiculoRepository vRepository;
	
	@Autowired
	FileRepository fRepository;
	@GetMapping("/criar_motorista")
	public String criarMotorista(ModelMap model) {
		model.addAttribute("motorista", new Motorista());
		model.addAttribute("veiculo", new Veiculo());
		return "motorista/criar_motorista";
	}
	
	
	
	@PostMapping("/salvar")
	@Transactional
	public String save(Motorista motorista,HttpSession session,RedirectAttributes attr,@RequestParam("documentoCad") MultipartFile documentoCad,@RequestParam("fotoCad") MultipartFile fotoCad) {
		try {
			
			String nomeDocumento=
					StringUtils.cleanPath(documentoCad.getOriginalFilename());
			String nomeFoto=
					StringUtils.cleanPath(fotoCad.getOriginalFilename());
			
			File arquivoBD=new File(
					null,nomeDocumento,documentoCad.getContentType(),documentoCad.getBytes());
			
			File fotoBD=new File(
					null,nomeFoto,fotoCad.getContentType(),fotoCad.getBytes());
			
		fRepository.save(arquivoBD);
		fRepository.save(fotoBD);
		
		if(motorista.getDocumento()!=null && motorista.getDocumento().getId()!=null && motorista.getDocumento().getId()>0) {
			fRepository.delete(motorista.getDocumento());
			
		}
		if(motorista.getFoto()!=null && motorista.getFoto().getId()!=null && motorista.getFoto().getId()>0) {
			fRepository.delete(motorista.getDocumento());
			
			
		}
		motorista.setDocumento(arquivoBD);
		motorista.setFoto(fotoBD);
		mRepository.save(motorista);
		attr.addFlashAttribute("msgSucess","Cadastro de motorista realizado com sucesso");} catch (Exception e) {
		// TODO: handle exception
	}
		

		return "redirect:/motorista/criar_motorista";
	}
	
	@GetMapping("/edicao/{id}")
	public String edition(@PathVariable("id") Integer motoristaId,HttpSession session,
			ModelMap model) {
		
		Motorista m=mRepository.findById(motoristaId).get();
		model.addAttribute("motorista", m)	;	
		return "/motorista/criar_motorista";
	}

	
	
	@ModelAttribute("veiculos")
	public List<Veiculo> getVeiculos(HttpSession session){
		return vRepository.findAll();
		
	}

}
