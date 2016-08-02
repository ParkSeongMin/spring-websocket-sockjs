package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

	@RequestMapping("/")
	public String index() {
	   return "forward:index.html";
	}
	
	@RequestMapping("/greetings")
	public String greetings() {
	   return "forward:index.html";
	}
}
