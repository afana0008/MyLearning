package com.example.mylearning.hello;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylearning.Exceptions.UserNotFoundException;
import com.example.mylearning.entities.Department;
import com.example.mylearning.entities.User;
import com.example.mylearning.repositories.DepartmentRepository;
import com.example.mylearning.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepo ;

	// get All Orders for a user

	@GetMapping("/department/{userid}")
	public List<Department> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		return userOptional.get().getDepartments();
	}

	// Create Order

	@PostMapping("/department/{userid}")
	public Department createOrder(@PathVariable Long userid, @RequestBody Department department) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		
		User user = userOptional.get();
		department.setUser(user);
		return departmentRepo.save(department);

	}

}
