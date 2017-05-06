package com.lynn.cat.mgmt.dal;

public class DALException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable ex) {
        super(message, ex);
    }

}
