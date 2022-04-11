package com.example.employee.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeExceptions.class)
	public ResponseEntity<Object> handleEmployeeException(EmployeeExceptions ex){
		return ResponseEntity.badRequest().body(ex.getMessage());	
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex){
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
	
}
