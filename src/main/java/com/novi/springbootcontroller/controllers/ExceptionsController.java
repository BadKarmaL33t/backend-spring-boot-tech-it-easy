package com.novi.springbootcontroller.controllers;

import com.novi.springbootcontroller.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.novi.springbootcontroller.controllers.TelevisionsController.televisionDataBase;

public class ExceptionsController {
    @ControllerAdvice
    public static class ExceptionController {
        @ExceptionHandler(value = RecordNotFoundException.class)
        public ResponseEntity<Object> exception(RecordNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public static void checkRecordExistence(int id) {
        if (!recordExistsInTelevisions(id)){
            throw new RecordNotFoundException("ID can't be found");
        }
    }

    public static boolean recordExistsInTelevisions(int id) {
        return televisionDataBase.contains(id);
    }
}
