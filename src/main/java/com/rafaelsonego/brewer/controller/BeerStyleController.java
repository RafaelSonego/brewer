package com.rafaelsonego.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaelsonego.brewer.model.BeerStyle;
import com.rafaelsonego.brewer.service.BeerStyleService;
import com.rafaelsonego.brewer.service.exception.BeerStyleException;

@Controller
public class BeerStyleController {

	@Autowired
	private BeerStyleService beerStyleService;

	@RequestMapping("/style/new")
	public ModelAndView redirectNewBeerStyle(BeerStyle beerStyle) {
		ModelAndView mv = new ModelAndView("beer/new-style");
		mv.addObject(beerStyle);
		return mv;
	}

	@RequestMapping(value = "/style/new", method = RequestMethod.POST)
	public ModelAndView newBeerStyle(@Valid BeerStyle beerStyle, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return redirectNewBeerStyle(beerStyle);
		}
		try {
			beerStyleService.save(beerStyle);
		} catch (BeerStyleException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return redirectNewBeerStyle(beerStyle);
		}

		attributes.addFlashAttribute("message", "Success");
		return new ModelAndView("redirect:/style/new");
	}
}
