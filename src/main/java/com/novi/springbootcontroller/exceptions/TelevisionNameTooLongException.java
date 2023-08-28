package com.novi.springbootcontroller.exceptions;

public class TelevisionNameTooLongException extends RuntimeException {
    public TelevisionNameTooLongException() {
        super();
    }

    public TelevisionNameTooLongException(String message) {
        super(message);
    }
}
