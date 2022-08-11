package com.gft.receitas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.receitas.entities.Receita;
import com.gft.receitas.services.IngredienteService;
import com.gft.receitas.services.ItemService;
import com.gft.receitas.services.ReceitaService;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping(path = "/manage")
	public ModelAndView editarReceita(@RequestParam (required=false) Long idReceita) {
		ModelAndView mv = new ModelAndView("receita/form");
		Receita receita;
		
		if(idReceita==null) {
			receita = new Receita();
		} else {
			try {
				receita = receitaService.obterReceita(idReceita);
			} catch (Exception e) {
				receita = new Receita();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("receita", receita);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/manage")
	public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("receita/form");
		boolean novo = true;
		Boolean naoTemNoBanco = false;
		Boolean temNoBanco = false;
		
		if(receita.getId() != null) {
			mv.addObject("receita", receita);
			novo = false;
		}
			
		if(bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
			return mv;
		}
		try {
			receitaService.salvarReceita(receita);
			naoTemNoBanco = true;
			mv.addObject("mensagem", "Drink salvo com sucesso!");
			mv.addObject("naoTemNoBanco", naoTemNoBanco);
		} catch (Exception e) {
			temNoBanco = true;
			mv.addObject("mensagem", "Esse Nome de Drink já existe no Banco de Dados!");
			mv.addObject("temNoBanco", temNoBanco);
		}
	

		if(novo) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receita);
		}
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarReceita(String nome, String ingrediente) {
		ModelAndView mv = new ModelAndView("receita/listar");
		mv.addObject("listaCompleta", itemService.listaItensCompletos());
		mv.addObject("listaIngredientes", ingredienteService.listaIngredienteCompleto());
		mv.addObject("listaReceita", receitaService.listaPesquisa(nome, ingrediente));
		mv.addObject("listaReceitaCompleta", receitaService.listaReceitaCompleta());
		return mv;
	}
	
	@RequestMapping(path="/excluir")
	public ModelAndView excluirReceita(@RequestParam Long idReceita, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/receita");
		Boolean temErro = false;
		Boolean semErro = false;
		
		try {
			receitaService.excluirReceita(idReceita);		
			semErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Drink excluído com sucesso!");
			redirectAttributes.addFlashAttribute("semErro", semErro);
		} catch (Exception e) {
			temErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir drink!" + e.getMessage());
			redirectAttributes.addFlashAttribute("temErro", temErro);
		}
		
		return mv;
	}
 }
