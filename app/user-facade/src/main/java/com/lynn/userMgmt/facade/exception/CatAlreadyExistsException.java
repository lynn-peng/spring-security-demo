package com.lynn.userMgmt.facade.exception;

public class CatAlreadyExistsException extends CatMgmtException {

    public CatAlreadyExistsException(String message) {
        super(message);
    }

    public CatAlreadyExistsException(String message, Throwable ex) {
        super(message, ex);
    }

}
