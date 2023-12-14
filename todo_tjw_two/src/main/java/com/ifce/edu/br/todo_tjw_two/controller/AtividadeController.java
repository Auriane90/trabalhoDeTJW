package com.ifce.edu.br.todo_tjw_two.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifce.edu.br.todo_tjw_two.model.Atividade;
import com.ifce.edu.br.todo_tjw_two.repository.AtividadeRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@GetMapping("/home/{id}")
	public String homeAtividade(@PathVariable("id") Long id, Model model){
		model.addAttribute("atividade", atividadeRepository.findAll());
		model.addAttribute("usuario_id", id);
		return "atividade/home-atividades";
	}
	
	@GetMapping("/novo/{id}")
	public String adicionarAtividade(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("atividade", new Atividade());
		model.addAttribute("usuario_id", id);
		return "atividade/cadastro-atividade";
	}
	
	@PostMapping("/salvar/{id}")
	public String salvarAtividade(@PathVariable("id") Long id, @Valid Atividade atividade, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "atividade/cadastro-atividade/"+id;
		}
		atividadeRepository.save(atividade);
		attributes.addFlashAttribute("mensagem", "Atividade salva com sucesso!");
		return "redirect:/atividade/novo/"+id;
	}
	
	
	@GetMapping("/apagar/{id}/{usuario_id}")
	public String deleteAtividade(@PathVariable("id") long id, @PathVariable("usuario_id") long usuario_id, Model model) {
		Atividade atividade = atividadeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		atividadeRepository.delete(atividade);
	    return "redirect:/atividade/home/"+usuario_id;
	}
	
	@GetMapping("/editar/{id}/{usuario_id}")
	public String editarAtividade(@PathVariable("id") long id, @PathVariable("usuario_id") long usuario_id, Model model) {
		Optional<Atividade> atividadeVelho = atividadeRepository.findById(id);
		if (!atividadeVelho.isPresent()) {
            throw new IllegalArgumentException("Atividade inválido:" + id);
        } 
		Atividade atividade = atividadeVelho.get();
	    model.addAttribute("atividade", atividade);
	    model.addAttribute("usuario_id", usuario_id);
	    return "/atividade/atividade-alterar";
	}
	
	@PostMapping("/editar/{id}/{usuario_id}")
	public String editarAtividade(@PathVariable("id") long id, @PathVariable("usuario_id") Long usuario_id,
			@Valid Atividade atividade, BindingResult result) {
		if (result.hasErrors()) {
	        return "/atividade/atividade-alterar";
	    }
	    atividadeRepository.save(atividade);
	    return "redirect:/atividade/home/"+usuario_id;
	}
}
