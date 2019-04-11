package com.techelevator.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnauthorizedException Class extends Exception and is used to throw
 * a custom exception when there is a forbidden Http Status Request
 */
@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class UnauthorizedException extends Exception {

    private static final long serialVersionUID = 1L;
}