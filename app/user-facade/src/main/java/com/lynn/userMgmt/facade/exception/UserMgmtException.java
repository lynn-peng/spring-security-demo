package com.lynn.userMgmt.facade.exception;

public abstract class UserMgmtException extends RuntimeException {

    public UserMgmtException(String message) {
        super(message);
    }

    public UserMgmtException(String message, Throwable ex) {
        super(message, ex);
    }
}
