package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/city")
public class CityController {

	@RequestMapping("/new")
	public String newCity() {
		return "beer/new-city";
	}
}
