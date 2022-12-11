package com.uss.minimarketspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String getEscritorio() {
		return "escritorio";
	}
	@GetMapping("/personas")
	public String getPersona() {
		return "persona";
	}


}
