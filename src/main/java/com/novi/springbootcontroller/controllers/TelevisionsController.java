package com.novi.springbootcontroller.controllers;

// TelevisionsController.java

import com.novi.springbootcontroller.exceptions.RecordNotFoundException;
import com.novi.springbootcontroller.exceptions.TelevisionNameTooLongException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionsController {
    final static List<String> televisionDataBase = new ArrayList<>();

    static {
        televisionDataBase.add("Sony");
        televisionDataBase.add("LG");
        televisionDataBase.add("Philips");
        televisionDataBase.add("Samsung");
        televisionDataBase.add("JVC");
        televisionDataBase.add("Panasonic");
    }

    @GetMapping(value = "/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        checkRecordExistence(id);
        return ResponseEntity.ok("television: " + televisionDataBase.get(id));
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) {
        if (television.length() > 20 && !televisionDataBase.contains(television)) {
            throw new TelevisionNameTooLongException("De naam van de televisie is te lang");
        } else {
            televisionDataBase.add(television);
        }
        return ResponseEntity.created(null).body("television");
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String television) {
        if (televisionDataBase.size() == 0 || id > televisionDataBase.size()) {
            throw new RecordNotFoundException("ID kon niet worden gevonden");
        } else {
            televisionDataBase.set(id, television);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }

    public static void checkRecordExistence(int id) {
        if (!recordExistsInTelevisions(id)) {
            throw new RecordNotFoundException("ID kon niet worden gevonden");
        }
    }

    public static boolean recordExistsInTelevisions(int id) {
        return id >= 0 && id < televisionDataBase.size();
    }
}


