package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionInputDto;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TelevisionsController {
    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping(value = "/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        List<TelevisionDto> televisionDtos;
        televisionDtos = televisionService.getAllTelevisions();

        return ResponseEntity.ok().body(televisionDtos);
    }

    // Return 1 television with a specific id
    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id") Long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);

        return ResponseEntity.ok().body(television);
    }

    @PostMapping("/televisions")
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto dto = televisionService.addTelevision(televisionInputDto);

//      Onderstaande code kan nu worden opgelost in jakarta.validation.constraints in de TelevisionInputDto class

//        if (television.getName().length() > 20 && !televisions.contains(television)) {
//            throw new TelevisionNameTooLongException("De naam van de televisie is te lang");
//        } else {
//            Television addedTelevision = televisionRepository.save(television);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("televisions/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto newTelevision) {
        TelevisionDto dto = televisionService.updateTelevision(id, newTelevision);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> updateTelevisionDetails(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto updatedTelevision) {
        TelevisionDto dto = televisionService.updateTelevisionDetails(id, updatedTelevision);

        return ResponseEntity.ok().body(dto);
    }
}