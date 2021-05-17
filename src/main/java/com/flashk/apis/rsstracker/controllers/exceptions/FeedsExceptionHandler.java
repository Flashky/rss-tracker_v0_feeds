package com.flashk.apis.rsstracker.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flashk.apis.rsstracker.controllers.model.ErrorResponse;
import com.flashk.apis.rsstracker.controllers.model.ErrorResponse.ErrorResponseBuilder;
import com.flashk.apis.rsstracker.controllers.model.InvalidFieldError;

@RestControllerAdvice
public class FeedsExceptionHandler extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<InvalidFieldError> invalidParameters = new ArrayList<>();
		
		ErrorResponseBuilder builder = ErrorResponse.builder()
				.title("There are validation errors")
				.detail("Please check  the following fields before sending a new request")
				.invalidParameters(invalidParameters);
		
		 ex.getBindingResult().getAllErrors().forEach((error) -> {
			 
		        String fieldName = ((FieldError) error).getField();
		        
			 	InvalidFieldError validationError = new InvalidFieldError();
			 	validationError.setObject(error.getObjectName());
			 	validationError.setField(fieldName);
			 	validationError.setMessage(error.getDefaultMessage());
		        
			 	invalidParameters.add(validationError);
		    });
		 
		return buildResponseWithBody(builder.build(), status);
	
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponseBuilder builder = ErrorResponse.builder()
				.title("Malformed or missing request body")
				.detail("The input JSON is malformed, please check before sending a new request");
		
		
		return buildResponseWithBody(builder.build(), status);
	}
	
	private ResponseEntity<Object> buildResponseWithBody(ErrorResponse response, HttpStatus status) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
		
		return ResponseEntity.status(status)
								.headers(headers)
								.body(response);
		
	}
}