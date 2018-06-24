package com.rafaelsonego.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaelsonego.brewer.model.Beer;
import com.rafaelsonego.brewer.model.Origin;
import com.rafaelsonego.brewer.model.Taste;
import com.rafaelsonego.brewer.repository.BeerStyleRepository;
import com.rafaelsonego.brewer.service.BeerService;
import com.rafaelsonego.brewer.service.exception.BeerExpetion;

@Controller
public class BeerControler {

	@Autowired
	private BeerStyleRepository beerStyleRepository;

	@Autowired
	private BeerService beerService;

	/***
	 * Redirect to templates/beer/NewBeer Page
	 * 
	 * @URL /beer/new
	 * @RequestMethod GET
	 * @param beer
	 * @return /beer/NewBeer
	 */
	@RequestMapping("/beer/new")
	public ModelAndView redirectNewBeer(Beer beer) {
		ModelAndView mv = new ModelAndView("beer/new-beer");
		mv.addObject("listTaste", Taste.values());
		mv.addObject("listStyle", beerStyleRepository.findAll());
		mv.addObject("listOrigin", Origin.values());
		return mv;
	}

	/***
	 * Create a new Beer
	 * 
	 * @RequestMethod GET
	 * @param beer
	 * @param result
	 *            Necessary to Validate object with @Valid annotation
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/beer/new", method = RequestMethod.POST)
	public ModelAndView newBeer(@Valid Beer beer, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return redirectNewBeer(beer);
		}

		try {
			beerService.save(beer);
		} catch (BeerExpetion e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return redirectNewBeer(beer);
		}

		attributes.addFlashAttribute("message", "Success");
		return new ModelAndView("redirect:/beer/new");
	}

}
