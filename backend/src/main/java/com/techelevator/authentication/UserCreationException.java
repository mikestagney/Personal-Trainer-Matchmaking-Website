package com.techelevator.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserCreationException Class extends Exception and is used to throw
 * a custom exception when there is a bad Http Status Request
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class UserCreationException extends Exception {

    public UserCreationException(String messages) {
        super(messages);
	}

	private static final long serialVersionUID = 7187525188973772939L;
}