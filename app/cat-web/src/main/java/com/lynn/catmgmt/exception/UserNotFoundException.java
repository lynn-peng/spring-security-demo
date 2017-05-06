package com.lynn.catmgmt.exception;

public class UserNotFoundException extends RestException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

}
