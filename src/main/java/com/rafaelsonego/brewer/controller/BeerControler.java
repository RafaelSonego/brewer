package com.rafaelsonego.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaelsonego.brewer.model.Beer;

@Controller
public class BeerControler {

	@RequestMapping("/beer/new")
	public String redirectNewBeer() {
		return "beer/NewBeer";
	}

	@RequestMapping(value = "/beer/new", method = RequestMethod.POST)
	public String newBeer(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Some Error!!!");
			return "beer/NewBeer"; //Page
		}
		attributes.addFlashAttribute("message","Success");
		return "redirect:/beer/new"; //Mapping Controller
	}

}
