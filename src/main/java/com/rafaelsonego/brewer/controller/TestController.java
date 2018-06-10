package com.rafaelsonego.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/test/sample-page")
	public String TestSamplePageUsingDefaultTemplate() {
		return "layout/sample-page-using-default-layout";
	}
	
}
