package com.lynn.userMgmt.facade.exception;

public class CatNotFoundException extends CatMgmtException {

    public CatNotFoundException(String message) {
        super(message);
    }

    public CatNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

}
