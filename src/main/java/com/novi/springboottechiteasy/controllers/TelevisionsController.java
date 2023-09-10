package com.novi.springboottechiteasy.controllers;

// TelevisionsController.java

import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.exceptions.TelevisionNameTooLongException;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedTelevision.getId())
                    .toUri();

            return ResponseEntity.created(location).body(addedTelevision);
        }
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable long id, @RequestBody Television updatedTelevision) {

        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("ID kon niet worden gevonden");
        } else {
            Television thisTelevision = television.get();
            thisTelevision.setType(updatedTelevision.getType());
            thisTelevision.setBrand(updatedTelevision.getBrand());
            thisTelevision.setName(updatedTelevision.getName());
            thisTelevision.setPrice(updatedTelevision.getPrice());
            thisTelevision.setAvailableSize(updatedTelevision.getAvailableSize());
            thisTelevision.setRefreshRate(updatedTelevision.getRefreshRate());
            thisTelevision.setScreenType(updatedTelevision.getScreenType());
            thisTelevision.setScreenQuality(updatedTelevision.getScreenQuality());
            thisTelevision.setSmartTv(updatedTelevision.getSmartTv());
            thisTelevision.setWifi(updatedTelevision.getWifi());
            thisTelevision.setVoiceControl(updatedTelevision.getVoiceControl());
            thisTelevision.setHdr(updatedTelevision.getHdr());
            thisTelevision.setBluetooth(updatedTelevision.getBluetooth());
            thisTelevision.setAmbiLight(updatedTelevision.getAmbiLight());
            thisTelevision.setOriginalStock(updatedTelevision.getOriginalStock());
            thisTelevision.setSold(updatedTelevision.getSold());

            Television saveTelevision = televisionRepository.save(thisTelevision);

            return ResponseEntity.ok().body(saveTelevision);
        }
    }


//    @DeleteMapping("/televisions/{id}")
//    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {
//        televisionDataBase.set(id, null);
//        return ResponseEntity.noContent().build();
//    }
    }


