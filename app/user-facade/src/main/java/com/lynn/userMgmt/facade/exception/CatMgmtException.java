package com.lynn.userMgmt.facade.exception;

public abstract class CatMgmtException extends RuntimeException {

    public CatMgmtException(String message) {
        super(message);
    }

    public CatMgmtException(String message, Throwable ex) {
        super(message, ex);
    }
}
