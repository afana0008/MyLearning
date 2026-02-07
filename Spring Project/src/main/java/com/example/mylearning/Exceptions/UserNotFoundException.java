package com.example.mylearning.Exceptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
        super(message);
    }
}
