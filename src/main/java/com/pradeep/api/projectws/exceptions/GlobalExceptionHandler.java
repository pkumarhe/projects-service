package com.pradeep.api.projectws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,  WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false),"DBLayer");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<?> resouceExistsException(ResourceExistsException ex,WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false),"DBLayer");		
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	

	@ExceptionHandler( {HttpStatusCodeException.class})
	@ResponseBody
	public ResponseEntity<Object> httpClientErrorException(HttpStatusCodeException e) throws IOException {
		ResponseEntity.BodyBuilder builder = ResponseEntity.status(e.getRawStatusCode()).header("X-Backend-Status", String.valueOf(e.getRawStatusCode()));
		if (e.getResponseHeaders().getContentType() != null) {
			builder.contentType(e.getResponseHeaders().getContentType());
		}
		return builder.body(e.getResponseBodyAsString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false),"DBLayer");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
