/**
 * 
 */
package com.demo.code.arbindnegi.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.code.arbindnegi.demo.dto.ApiResponse;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    //ResourceNotFoundException is a custome created java class. whenever the API will throw ResourceNotFoundException 
    //than the below Exception handller will execute.
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> getResourceNotFoundException(ResourceNotFoundException exception) {
	String message = exception.getMessage();
	ApiResponse apiResponse = new ApiResponse(message, false);
	
	return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
	Map<String, String> response = new HashMap<>();
	exception.getBindingResult().getAllErrors().forEach((error) -> {
	    String filedName = ((FieldError)error).getField();
	    String message = error.getDefaultMessage();
	    
	    response.put(filedName, message);
	});
	
	
	return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ApiExceptionHanddler.class)
    public ResponseEntity<ApiResponse> handleExceptionManageException(ApiExceptionHanddler exception) {
	String message = exception.getMessage();
	ApiResponse apiResponse = new ApiResponse(message, false);
	
	return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
