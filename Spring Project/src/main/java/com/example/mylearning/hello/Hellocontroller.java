package com.example.mylearning.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylearning.entities.User;
import com.example.mylearning.services.UserService;

@RestController
@RequestMapping("/login")
public class Hellocontroller {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
	

	@GetMapping("/hello-string")
	public String helloString() {
		return new UserDetails("Afana", "fathima", "Chennai").toString();
	}
	
	@GetMapping("/get")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/hello-bean")
	public UserDetails helloBean() {
		return new UserDetails("Afana", "fathima", "Chennai");
	}

}
