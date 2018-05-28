package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeerControler {

	@RequestMapping("/beer/new")
	public String newBeer() {
		return "beer/NewBeer";
	}
	
}
