package com.rafaelsonego.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaelsonego.brewer.model.BeerStyle;
import com.rafaelsonego.brewer.service.BeerStyleService;
import com.rafaelsonego.brewer.service.exception.BeerStyleException;

@Controller
@RequestMapping("/style")
public class BeerStyleController {

	@Autowired
	private BeerStyleService beerStyleService;

	@RequestMapping("/new")
	public ModelAndView redirectNewBeerStyle(BeerStyle beerStyle) {
		ModelAndView mv = new ModelAndView("beer/new-style");
		mv.addObject(beerStyle);
		return mv;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newBeerStyle(@Valid BeerStyle beerStyle, BindingResult result, RedirectAttributes attributes) {
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

	@RequestMapping(value="/new/modal", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> saveBeerStyleModal(@RequestBody @Valid BeerStyle beerStyle, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("name").getDefaultMessage());
		}
		try {
			beerStyle = beerStyleService.save(beerStyle);
		} catch (BeerStyleException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok(beerStyle);
				
	}

}
