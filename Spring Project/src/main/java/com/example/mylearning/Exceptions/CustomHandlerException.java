package com.example.mylearning.Exceptions;

import java.util.Date;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.mylearning.Exceptions.CustomErrorDetails;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Null;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler{
	
	@Override
	protected @Null ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.info("handleMethodArgumentNotValid");
		return handleExceptionInternal(ex, null, headers, status, request);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in GEH - Method Not allowed", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}
	
	// UserNameNotFoundException
		@ExceptionHandler(IllegalArgumentException.class)
		public final ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex,
				WebRequest request) {
			
			return ResponseEntity.badRequest().body("Error::"+ex.getMessage());

		}

		// ConstraintViolationException
		@ExceptionHandler(ConstraintViolationException.class)
		public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
				WebRequest request) {
			CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
					request.getDescription(false));

			return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

		}
		
		@ExceptionHandler(UserNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public CustomErrorDetails usernameNotFound(IllegalArgumentException ex) {
			
			return new CustomErrorDetails(new Date(), "From @ControllerAdvice NOT FOUND", 
					ex.getMessage());
		}



}
