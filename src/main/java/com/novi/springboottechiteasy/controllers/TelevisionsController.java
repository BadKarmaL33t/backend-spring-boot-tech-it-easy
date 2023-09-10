package com.novi.springboottechiteasy.controllers;

// TelevisionsController.java

import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.exceptions.TelevisionNameTooLongException;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionsController {
    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping(value = "/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions() {
        List<Television> televisions;
        televisions = televisionRepository.findAll();

        return ResponseEntity.ok(televisions);
    }

    // Return 1 television with a specific id
    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            Television televisionMatch = television.get();

            return ResponseEntity.ok().body(televisionMatch);
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        List<Television> televisions;
        televisions = televisionRepository.findAll();

        if (television.getName().length() > 20 && !televisions.contains(television)) {
            throw new TelevisionNameTooLongException("De naam van de televisie is te lang");
        } else {
            Television addedTelevision = televisionRepository.save(television);

            return ResponseEntity.created(null).body(addedTelevision);
        }
    }

//    @PutMapping("televisions/{id}")
//    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody String television) {
//        if (televisionDataBase.size() == 0 || id > televisionDataBase.size()) {
//            throw new RecordNotFoundException("ID kon niet worden gevonden");
//        } else {
//            televisionDataBase.set(id, television);
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/televisions/{id}")
//    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {
//        televisionDataBase.set(id, null);
//        return ResponseEntity.noContent().build();
//    }
}


