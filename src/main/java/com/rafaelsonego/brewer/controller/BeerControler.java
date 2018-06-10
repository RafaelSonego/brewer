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

	/***
	 * Redirect to templates/beer/NewBeer Page
	 * @URL /beer/new
	 * @RequestMethod GET
	 * @param beer
	 * @return /beer/NewBeer
	 */
	@RequestMapping("/beer/new")
	public String redirectNewBeer(Beer beer) {
		return "beer/new-beer";
	}

	/***
	 * Create a new Beer
	 * @RequestMethod GET
	 * @param beer
	 * @param result Necessary to Validate object with @Valid annotation
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/beer/new", method = RequestMethod.POST)
	public String newBeer(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return redirectNewBeer(beer);
		}
		attributes.addFlashAttribute("message","Success");
		return "redirect:/beer/new"; //Mapping Controller
	}
}
