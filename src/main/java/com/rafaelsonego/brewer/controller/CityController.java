package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityController {

	@RequestMapping("/city/new")
	public String newCity() {
		return "beer/new-city";
	}
}
