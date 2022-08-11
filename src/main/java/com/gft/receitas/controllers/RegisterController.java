package com.gft.receitas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.receitas.entities.User;
import com.gft.receitas.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("security/register.html");
		
		mv.addObject("user", new User());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView newPerson(@Valid User user, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("security/register.html");
		
		user.getRoles().add("user");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("user", user);
			return mv;
		}
		
		userService.registrar(user);
		
		mv.addObject("mensagem", "Conta criada com sucesso!");
				
		return mv;
	}
	
//	@Autowired
//	UserService userService;
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView register() {
//		ModelAndView mv = new ModelAndView("security/register.html");
//		
//		mv.addObject("user", new User());
//		
//		return mv;
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView newPerson(User user, RedirectAttributes redirectAttributes) {
//		ModelAndView mv = new ModelAndView("redirect:/register");
//		
//		user.getRoles().add("user");
//		
//		userService.registrar(user);
//		
//		redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
//				
//		return mv;
//	}

}
