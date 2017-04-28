package com.lynn.userMgmt.facade.exception;

public class UserAlreadyExistsException extends UserMgmtException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable ex) {
        super(message, ex);
    }

}
