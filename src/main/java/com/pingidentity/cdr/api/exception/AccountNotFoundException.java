package com.pingidentity.cdr.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037337771929156850L;
	
	public AccountNotFoundException(String msg)
	{
		super(msg);
	}
	
	public AccountNotFoundException(String msg, Throwable t)
	{
		super(msg, t);
	}

	public int getCode() {
		return 404;
	}

}
