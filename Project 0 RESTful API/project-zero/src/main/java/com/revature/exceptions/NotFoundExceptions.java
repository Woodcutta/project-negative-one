package com.revature.exceptions;

public class NotFoundExceptions extends RuntimeException{

	public NotFoundExceptions() {
		super();
	}

	public NotFoundExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public NotFoundExceptions(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NotFoundExceptions(String message) {
		super(message);
		
	}

	public NotFoundExceptions(Throwable cause) {
		super(cause);
		
	}
	
}
