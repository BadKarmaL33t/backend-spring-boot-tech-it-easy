package com.novi.springbootcontroller.controllers;

import com.novi.springbootcontroller.exceptions.RecordNotFoundException;
import com.novi.springbootcontroller.exceptions.TelevisionNameTooLongException;
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

        @ExceptionHandler(value = IndexOutOfBoundsException.class)
        public ResponseEntity<String> exception(IndexOutOfBoundsException exception) {
            return new ResponseEntity<>("ID is niet bekend in de database", HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = TelevisionNameTooLongException.class)
        public ResponseEntity<String> exception(TelevisionNameTooLongException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

