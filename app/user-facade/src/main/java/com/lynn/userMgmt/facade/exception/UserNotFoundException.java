package com.lynn.userMgmt.facade.exception;

public class UserNotFoundException extends UserMgmtException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

}
