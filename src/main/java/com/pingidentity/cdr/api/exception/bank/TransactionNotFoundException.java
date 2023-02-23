package com.pingidentity.cdr.api.exception.bank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037337771929156850L;
	
	public TransactionNotFoundException(String msg)
	{
		super(msg);
	}
	
	public TransactionNotFoundException(String msg, Throwable t)
	{
		super(msg, t);
	}

	public int getCode() {
		return 404;
	}

}
