package com.pingidentity.cdr.api.exception.bank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PayeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037337771929156850L;
	
	public PayeeNotFoundException(String msg)
	{
		super(msg);
	}
	
	public PayeeNotFoundException(String msg, Throwable t)
	{
		super(msg, t);
	}

	public int getCode() {
		return 404;
	}

}
