package com.novi.springboottechiteasy.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
