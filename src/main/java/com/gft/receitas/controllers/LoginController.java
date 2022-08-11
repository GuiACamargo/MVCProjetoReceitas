package com.gft.receitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.receitas.services.ReceitaService;

@Controller
public class LoginController {
	
	@Autowired
	ReceitaService receitaService;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("security/login.html");
		return mv;
	}
	
	@RequestMapping("/popular")
	public ModelAndView popularBanco(RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/receita");
		Boolean naoTemNoBanco = false;
		Boolean temNoBanco = false;
		
		try {
			receitaService.popularBanco();
			naoTemNoBanco = true;
			redirectAttributes.addFlashAttribute("mensagem", "Banco Populado com sucesso!");
			redirectAttributes.addFlashAttribute("naoTemNoBanco", naoTemNoBanco);
		} catch (Exception e) {
			temNoBanco = true;
			redirectAttributes.addFlashAttribute("mensagem", "Erro! Banco de Dados pode já ter sido Populado!");
			redirectAttributes.addFlashAttribute("temNoBanco", temNoBanco);
		}

		return mv;
	}

}
