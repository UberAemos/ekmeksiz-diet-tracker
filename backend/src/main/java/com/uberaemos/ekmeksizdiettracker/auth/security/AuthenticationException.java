package com.uberaemos.ekmeksizdiettracker.auth.security;

public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message, Throwable cause ) {
		super(message, cause);
	}
}
