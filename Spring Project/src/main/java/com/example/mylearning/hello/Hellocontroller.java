package com.example.mylearning.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontroller {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
	

	@GetMapping("/hello-string")
	public String helloString() {
		return new UserDetails("Afana", "fathima", "Chennai").toString();
	}
	@GetMapping("/hello-bean")
	public UserDetails helloBean() {
		return new UserDetails("Afana", "fathima", "Chennai");
	}

}
