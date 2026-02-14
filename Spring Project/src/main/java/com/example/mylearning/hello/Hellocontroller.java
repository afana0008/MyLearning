package com.example.mylearning.hello;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylearning.entities.User;
import com.example.mylearning.services.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/login")
@Tag(name = "User API")
public class Hellocontroller {
	
	@Autowired
	private final UserService service;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	
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
	
	@Hidden
	@GetMapping("/hello-bean")
	public UserDetails helloBean() {
		return new UserDetails("Afana", "fathima", "Chennai");
	}

	@GetMapping("/get") //Read data
	public List<User> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/swagger/get/{id}") //Read data
	@Operation(summary = "Get user by ID")
    @ApiResponse(responseCode = "200", description = "Success")
	public List<User> getAllwithParam(@Parameter(description = "User ID", example = "1") @PathVariable("id") Long id) {
		return service.getAll();
	}
	
	@PostMapping("/create") // Create or submit data
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User Createuser=service.CreateUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(Createuser);
	}
	
	@PutMapping("/update/{id}") //Update entire resource
	public ResponseEntity<String>  UpdateUserPut(@PathVariable("id") long rollno, @RequestParam String name) {
		User Updateuser=service.updateUser(rollno, name);
		String result = null;
		if(Updateuser!=null) {
			result="Put Updated";
		}
		return ResponseEntity.ok(result);
	}
	
	@PatchMapping("/update/{id}") // Update part of resource
	public ResponseEntity<String>  UpdateUserPatch(@PathVariable("id") long rollno, @RequestParam String name) {
		User Updateuser=service.updateUser(rollno, name);
		String result = null;
		if(Updateuser!=null) {
			result="patch Updated";
		}
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/remove/{id}") //Remove data
	public ResponseEntity<String>  UpdateUserPatch(@PathVariable("id") long rollno) {
		boolean remove=service.removeUser(rollno);
		String response = null;
		if(remove)
			response="Remove successfully";
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(Locale locale) {
	    return messageSource.getMessage("label.hello", null, locale.FRENCH);
	}

	
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale().FRANCE);
		
	}

}
