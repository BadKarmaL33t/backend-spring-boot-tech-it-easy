package com.novi.springbootcontroller.controllers;

// TelevisionsController.java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionsController {
    static List<Integer> televisionDataBase = new ArrayList<>();
    static {
        televisionDataBase.add(1111);
        televisionDataBase.add(1112);
        televisionDataBase.add(1113);
        televisionDataBase.add(1114);
        televisionDataBase.add(1115);
    }

    @GetMapping(value = "/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        return ResponseEntity.ok("televisions");
    }

    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        ExceptionsController.checkRecordExistence(id);
            return ResponseEntity.ok("television: " + id);
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) {
        int id = 0;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/televisions/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).body("television");
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String television) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}


