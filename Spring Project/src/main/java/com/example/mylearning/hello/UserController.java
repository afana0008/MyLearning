package com.example.mylearning.hello;



import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mylearning.entities.User;
import com.example.mylearning.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	public UserController(UserService userservice) {
		this.userservice=userservice;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long rollno) {
		System.out.println("rollno"+rollno);
		User Createuser = userservice.getUserById(rollno)
        .orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with id: " + rollno
                )
        );
	    return ResponseEntity.ok().body(Createuser);
	}


	@PostMapping("/create")
	public ResponseEntity<Void> createUser( @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userservice.CreateUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getCity()).toUri());
			
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		} catch (NoSuchElementException ex) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND,
	                "User not found with id: " + user.getRollno()
	        );
	    }
	}

		
	
}
