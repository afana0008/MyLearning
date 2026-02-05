package com.example.mylearning.hello;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mylearning.Exceptions.UserExistsException;
import com.example.mylearning.entities.User;
import com.example.mylearning.services.UserService;

public class UserController {

	@Autowired
	private UserService userservice;
	private UserExistsException exception1;
	public UserController(UserService userservice) {
		this.userservice=userservice;
	}

	@PostMapping
	public ResponseEntity<Void> createUser( @RequestBody User user, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		try {
			userservice.CreateUser(user);
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getRollno()).toUri());
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
