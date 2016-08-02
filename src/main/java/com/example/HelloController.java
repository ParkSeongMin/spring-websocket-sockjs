package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/greetings")
	public String greetings() {
		return "Greetings from Spring Boot!";
	}

}
