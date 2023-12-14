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

import com.ifce.edu.br.todo_tjw_two.repository.TarefaRepository;

import jakarta.validation.Valid;

import com.ifce.edu.br.todo_tjw_two.model.Tarefa;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping("/home/{atividade_id}")
	public String homeTarefa(@PathVariable("atividade_id") Long atividade_id, Model model) {
		model.addAttribute("tarefa", tarefaRepository.buscaPorAtividade_idComParam(atividade_id));
		model.addAttribute("atividade_id", atividade_id);
		model.addAttribute("usuario_id", tarefaRepository.buscaPorUsuarioIdComParam(atividade_id));
		return "tarefa/home-tarefa";
	}
	
	@GetMapping("/novo/{atividade_id}")
	public String adicionarTarefa(@PathVariable("atividade_id") Long atividade_id, ModelMap model) {
		model.addAttribute("tarefa", new Tarefa());
		model.addAttribute("atividade_id", atividade_id);
		model.addAttribute("situacao", false);
		return "tarefa/cadastro-tarefa";
	}
	
	@PostMapping("/salvar/{atividade_id}")
	public String salvarTarefa(@PathVariable("atividade_id") Long atividade_id, @Valid Tarefa tarefa, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "tarefa/cadastro-tarefa/"+atividade_id;
		}
		tarefaRepository.save(tarefa);
		attributes.addFlashAttribute("mensagem", "Tarefa salva com sucesso!");
		return "redirect:/tarefa/novo/"+atividade_id;
	}
	
	@GetMapping("/apagar/{id}/{atividade_id}")
	public String deleteTarefa(@PathVariable("id") long id, @PathVariable("atividade_id") long atividade_id, Model model) {
		Tarefa tarefa = tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		tarefaRepository.delete(tarefa);
	    return "redirect:/tarefa/home/"+atividade_id;
	}
	
	@GetMapping("/editar/{id}/{atividade_id}")
	public String editarTarefa(@PathVariable("id") long id, @PathVariable("atividade_id") long atividade_id, Model model) {
		Optional<Tarefa> tarefaVelho = tarefaRepository.findById(id);
		if (!tarefaVelho.isPresent()) {
            throw new IllegalArgumentException("Tarefa inválido:" + id);
        } 
		Tarefa tarefa = tarefaVelho.get();
	    model.addAttribute("tarefa", tarefa);
	    model.addAttribute("usuario_id", atividade_id);
	    return "/tarefa/tarefa-alterar";
	}
	
	@PostMapping("/editar/{id}/{atividade_id}")
	public String editarTarefa(@PathVariable("id") long id, @PathVariable("atividade_id") Long atividade_id,
			@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasErrors()) {
	        return "/tarefa/tarefa-alterar";
	    }
	    tarefaRepository.save(tarefa);
	    return "redirect:/tarefa/home/"+atividade_id;
	}
	
	@GetMapping("/concluido/{id}/{atividade_id}")
	public String marcarTarefaP(@PathVariable("id") long id, @PathVariable("atividade_id") long atividade_id, Model model) {
		Tarefa tarefa = tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		tarefa.setSituacao_tarefa(true);
		tarefaRepository.save(tarefa);
	    return "redirect:/tarefa/home/"+atividade_id;
	}
	
	@GetMapping("/desconcluido/{id}/{atividade_id}")
	public String marcarTarefaN(@PathVariable("id") long id, @PathVariable("atividade_id") long atividade_id, Model model) {
		Tarefa tarefa = tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		tarefa.setSituacao_tarefa(false);
		tarefaRepository.save(tarefa);
	    return "redirect:/tarefa/home/"+atividade_id;
	}
}
