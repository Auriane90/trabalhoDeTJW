package com.ifce.edu.br.todo_tjw_two.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("mensagemhello", "Sejam Bem vindos :)");
		return "home";
	}
	
	
}
