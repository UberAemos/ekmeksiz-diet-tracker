package com.uberaemos.ekmeksizdiettracker.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameAlreadyExistsException extends AuthenticationException {

	public UsernameAlreadyExistsException(String msg) {
		super(msg);
	}

}
