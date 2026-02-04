package com.example.mylearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mylearning.entities.User;
import com.example.mylearning.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	public UserService(UserRepository repo) {
		this.repo=repo;
	}

	public List<User> getAll() {
		return repo.findAll();
	}
	
	public User CreateUser(User user) {
		return repo.save(user);
	}
	
	public User updateUser(Long rollno, String name) {
		
		User user = repo.getById(rollno);
		user.setFirstname(name);;
		return repo.save(user);
	}
}
