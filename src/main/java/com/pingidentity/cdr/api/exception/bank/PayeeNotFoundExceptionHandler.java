package com.pingidentity.cdr.api.exception.bank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pingidentity.cdr.api.exception.ErrorResponse;

@ControllerAdvice
public class PayeeNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { PayeeNotFoundException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("Could not locate payee"), HttpStatus.NOT_FOUND);
	}
}