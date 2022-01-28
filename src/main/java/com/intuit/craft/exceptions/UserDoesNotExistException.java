package com.intuit.craft.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String exception) {
        super(exception);
    }
}
