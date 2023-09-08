package com.novi.springboottechiteasy.controllers;

// TelevisionsController.java

import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
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

    // Return 1 televisie met een specifiek id
    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);
        // Gecheckt in de uitwerkingen hoe we hiermee verder kunnen om wat vooruit te leren.
        // Check of de optional empty is. Het tegenovergestelde alternatief is "television.isPresent()"
        if (television.isEmpty()) {

            // Als er geen television in de optional staat, roepen we hier de RecordNotFoundException constructor aan met message.
            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            // Als er wel een television in de optional staat, dan halen we die uit de optional met de get-methode.
            Television televisionMatch = television.get();

            // Return de television en een 200 status
            return ResponseEntity.ok().body(televisionMatch);
        }
    }
}

//    @PostMapping("/televisions")
//    public ResponseEntity<Television> addTelevision(@RequestBody String television) {
//        if (television.length() > 20 && !televisionDataBase.contains(television)) {
//            throw new TelevisionNameTooLongException("De naam van de televisie is te lang");
//        } else {
//            televisionDataBase.add(television);
//        }
//        return ResponseEntity.created(null).body("television");
//    }
//
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


