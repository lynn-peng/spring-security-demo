package com.lynn.catmgmt.exception;

public class RestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable ex) {
        super(message, ex);
    }
}
