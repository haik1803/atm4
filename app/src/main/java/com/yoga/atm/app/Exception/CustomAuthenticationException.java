package com.yoga.atm.app.Exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 665106578036637454L;

	public CustomAuthenticationException(String message) {
		super(message);
	}
}
