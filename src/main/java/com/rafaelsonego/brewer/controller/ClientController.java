package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
	
	@RequestMapping("/client/new")
	public String newClient() {
		return "beer/new-client";
	}

}
