package com.example.mylearning.hello;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mylearning.Exceptions.UserNotFoundException;
import com.example.mylearning.dto.UserDtoV1;
import com.example.mylearning.dto.UserDtoV2;
import com.example.mylearning.entities.Department;
import com.example.mylearning.entities.User;
import com.example.mylearning.repositories.UserRepository;
import com.example.mylearning.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("versioning/users")
public class UserUriVersionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/test")
	public String test() {
	    return "Working";
	}
	//URI based Versioning -  V1
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}" })
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

				 Optional<User> userOptional = userService.getUserById(id);
				
				if(!userOptional.isPresent()) {
					throw new UserNotFoundException("user not found");
				}
				
				User user = userOptional.get();
			
			UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
			return userDtoV1;
	
	}
	
	
	//URI based Versioning -  V2
	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

				 Optional<User> userOptional = userService.getUserById(id);
				
				if(!userOptional.isPresent()) {
					throw new UserNotFoundException("user not found");
				}
				
				User user = userOptional.get();
			
			UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
			return userDtoV2;
	
	}
	
	//Request Parameter based Versioning -  V1
		@GetMapping(value = "/param/{id}", params = "version=1")
		public UserDtoV1 getUserByIdParam(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

					 Optional<User> userOptional = userService.getUserById(id);
					
					if(!userOptional.isPresent()) {
						throw new UserNotFoundException("user not found");
					}
					
					User user = userOptional.get();
				
				UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
				return userDtoV1;
		
		}
		
		
		//Request Paramter based Versioning -  V2
		@GetMapping(value = "/param/{id}", params = "version=2")
		public UserDtoV2 getUserById2param(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

					 Optional<User> userOptional = userService.getUserById(id);
					
					if(!userOptional.isPresent()) {
						throw new UserNotFoundException("user not found");
					}
					
					User user = userOptional.get();
				
				UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
				return userDtoV2;
		
		}

		// Custom Header based Versioning - V1
		@GetMapping(value = "/header/{id}", headers = "API-VERSION=1")
		public UserDtoV1 getUserByIdheader(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("user not found");
			}

			User user = userOptional.get();

			UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
			return userDtoV1;

		}

		// Custom Header based Versioning - V2
		@GetMapping(value = "/header/{id}", headers = "API-VERSION=2")
		public UserDtoV2 getUserById2header(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("user not found");
			}

			User user = userOptional.get();

			UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
			return userDtoV2;

		}


		// Media Type Versioning - V1
		@GetMapping(value = "/media/{id}", produces="application/vnd.api-v1+json")
		public UserDtoV1 getUserByIdmeadia(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("user not found");
			}

			User user = userOptional.get();

			UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
			return userDtoV1;

		}

		// Media Type Versioning - V2
		@GetMapping(value = "/media/{id}", produces="application/vnd.api-v2+json")
		public UserDtoV2 getUserById2media(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("user not found");
			}

			User user = userOptional.get();

			UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
			return userDtoV2;

		}

		@GetMapping("/content/getAll") //Read data
		public List<User> getAll() {
			return userService.getAll();
		}
		
		@PostMapping("/content/create") // Create or submit data
		public ResponseEntity<User> createUser(@RequestBody User user) {
			User Createuser=userService.CreateUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(Createuser);
		}

}
