package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StyleController {

	@RequestMapping("/style/new")
	public String newStyle() {
		return "beer/new-style";
	}
	
}
