package com.example.mylearning.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylearning.entities.User;
import com.example.mylearning.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/login")
public class Hellocontroller {
	
	@Autowired
	private final UserService service;
	
	public Hellocontroller(UserService service) {
		this.service=service;
	}

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

	@GetMapping("/get")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User Createuser=service.CreateUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(Createuser);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String>  UpdateUser(@PathVariable("id") long rollno, @RequestParam String name) {
		User Updateuser=service.updateUser(rollno, name);
		String result = null;
		if(Updateuser!=null) {
			result="Updated";
		}
		return ResponseEntity.ok(result);
	}
}
