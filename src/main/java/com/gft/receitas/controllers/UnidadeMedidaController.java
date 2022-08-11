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

import com.gft.receitas.entities.UnidadeMedida;
import com.gft.receitas.services.UnidadeMedidaService;

@Controller
@RequestMapping("/unidadeMedida")
public class UnidadeMedidaController {


	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	@RequestMapping(path = "/manage")
	public ModelAndView editarUnidadeMedida(@RequestParam (required=false) Long id) {
		ModelAndView mv = new ModelAndView("unidadeMedida/form");
		UnidadeMedida unidadeMedida;
		
		if(id==null) {
			unidadeMedida = new UnidadeMedida();
		} else {
			try {
				unidadeMedida = unidadeMedidaService.obterUnidadeMedida(id);
			} catch (Exception e) {
				unidadeMedida = new UnidadeMedida();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("unidadeMedida", unidadeMedida);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/manage")
	public ModelAndView salvarUnidadeMedida(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("unidadeMedida/form");
		boolean novo = true;
		Boolean naoTemNoBanco = false;
		Boolean temNoBanco = false;
		
		if(unidadeMedida.getId() != null) {
			mv.addObject("unidadeMedida", unidadeMedida);
			novo = false;
		}
			
		if(bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
			return mv;
		}
		
		try {
			unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
			naoTemNoBanco = true;
			mv.addObject("mensagem", "Unidade de Medida salva com sucesso!");
			mv.addObject("naoTemNoBanco", naoTemNoBanco);
		} catch (Exception e) {
			temNoBanco = true;
			mv.addObject("mensagem", "Essa Unidade de Medida já existe no Banco de Dados!");
			mv.addObject("temNoBanco", temNoBanco);
		}
		
		if(novo) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		} else {
			mv.addObject("unidadeMedida", unidadeMedida);
		}

		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarIngredientes(String nome) {
		ModelAndView mv = new ModelAndView("unidadeMedida/listar");
		mv.addObject("lista", unidadeMedidaService.listaUnidadeMedida(nome));
		mv.addObject("nome", nome);
		mv.addObject("listaCompleta", unidadeMedidaService.listaUnidadeMedidaCompleto());
		return mv;
	}
	
	@RequestMapping(path="/excluir")
	public ModelAndView excluirUnidadeMedida(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/unidadeMedida");
		Boolean temErro = false;
		Boolean semErro = false;
		
		try {
			unidadeMedidaService.excluirUnidadeMedida(id);
			semErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Unidade de Medida excluída com sucesso!");
			redirectAttributes.addFlashAttribute("semErro", semErro);
		} catch (Exception e) {
			temErro = true;
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir Unidade de Medida! Confira se ele não está associado a um Drink");
			redirectAttributes.addFlashAttribute("temErro", temErro);
		}
		
		return mv;
	}
}
