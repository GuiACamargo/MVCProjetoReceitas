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

import com.gft.receitas.entities.Ingrediente;
import com.gft.receitas.services.IngredienteService;

@Controller
@RequestMapping(path="/ingrediente")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(path = "/manage")
	public ModelAndView editarIngrediente(@RequestParam (required=false) Long id) {
		ModelAndView mv = new ModelAndView("ingrediente/form");
		Ingrediente ingrediente;
		
		if(id==null) {
			ingrediente = new Ingrediente();
		} else {
			try {
				ingrediente = ingredienteService.obterIngrediente(id);
			} catch (Exception e) {
				ingrediente = new Ingrediente();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("ingrediente", ingrediente);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/manage")
	public ModelAndView salvarIngrediente(@Valid Ingrediente ingrediente, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("ingrediente/form");
		boolean novo = true;
		Boolean naoTemNoBanco = false;
		Boolean temNoBanco = false;
		
		if(ingrediente.getId() != null) {
			mv.addObject("ingrediente", ingrediente);
			novo = false;
		}
			
		if(bindingResult.hasErrors()) {
			mv.addObject("ingrediente", ingrediente);
			return mv;
		}
		try {
			ingredienteService.salvarIngrediente(ingrediente);
			naoTemNoBanco = true;
			mv.addObject("mensagem", "Ingrediente salvo com sucesso!");
			mv.addObject("naoTemNoBanco", naoTemNoBanco);
		} catch (Exception e) {
			temNoBanco = true;
			mv.addObject("mensagem", "Esse Ingrediente já existe no Banco de Dados!");
			mv.addObject("temNoBanco", temNoBanco);
		}
	
		
		if(novo) {
			mv.addObject("ingrediente", new Ingrediente());
		} else {
			mv.addObject("ingrediente", ingrediente);
		}

		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarIngredientes(String nome) {
		ModelAndView mv = new ModelAndView("ingrediente/listar");
		mv.addObject("lista", ingredienteService.listaIngrediente(nome));
		mv.addObject("nome", nome);
		mv.addObject("listaCompleta", ingredienteService.listaIngredienteCompleto());
		return mv;
	}
	
	@RequestMapping(path="/excluir")
	public ModelAndView excluirIngrediente(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/ingrediente");
		Boolean temErro = false;
		Boolean semErro = false;
		
		try {
			ingredienteService.excluirIngrediente(id);
			semErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Ingrediente excluído com sucesso!");
			redirectAttributes.addFlashAttribute("semErro", semErro);
		} catch (Exception e) {
			temErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir ingrediente! Confira se ele não está associado a um Drink");
			redirectAttributes.addFlashAttribute("temErro", temErro);		
		}
		
		return mv;
	}
}
