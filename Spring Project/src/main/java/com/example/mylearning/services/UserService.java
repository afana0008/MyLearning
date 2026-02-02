package com.example.mylearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.mylearning.entities.User;
import com.example.mylearning.repositories.UserRepository;

public class UserService {

	@Autowired
	private UserRepository repo;
	public UserService(UserRepository repo) {
		this.repo=repo;
	}

	public List<User> getAll() {
		return repo.findAll();
	}
}
