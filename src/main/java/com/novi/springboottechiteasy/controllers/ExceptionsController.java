package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.exceptions.TelevisionNameTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionsController {
    @ControllerAdvice
    public static class ExceptionController {
        @ExceptionHandler(value = RecordNotFoundException.class)
        public ResponseEntity<String> exception(RecordNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

//        @ExceptionHandler(value = IndexOutOfBoundsException.class)
//        public ResponseEntity<String> exception(IndexOutOfBoundsException exception) {
//            return new ResponseEntity<>("ID couldn't be found in the database", HttpStatus.NOT_FOUND);
//        }

        @ExceptionHandler(value = TelevisionNameTooLongException.class)
        public ResponseEntity<String> exception(TelevisionNameTooLongException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

