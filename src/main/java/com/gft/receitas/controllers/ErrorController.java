package com.gft.receitas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/access-denied")
public class ErrorController {
	
	@RequestMapping
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView("security/error.html");
		return mv;
	}

}
