package com.pingidentity.cdr.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AccountNotFoundException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("Could not locate account"), HttpStatus.NOT_FOUND);
	}
}