package com.example.ERPSpringBootBackEnd.exception;

import javax.naming.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
