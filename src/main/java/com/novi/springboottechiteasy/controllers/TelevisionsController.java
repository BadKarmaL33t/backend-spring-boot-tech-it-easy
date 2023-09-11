package com.novi.springboottechiteasy.controllers;

// TelevisionsController.java

import com.novi.springboottechiteasy.dtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.TelevisionInputDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.exceptions.TelevisionNameTooLongException;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        TelevisionDto inputDto = televisionService.addTelevision(televisionInputDto);
//
//      Onderstaande code kan nu worden opgelost in jakarta.validation.constraints in de TelevisionInputDto class

//        if (television.getName().length() > 20 && !televisions.contains(television)) {
//            throw new TelevisionNameTooLongException("De naam van de televisie is te lang");
//        } else {
//            Television addedTelevision = televisionRepository.save(television);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(inputDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(inputDto);
    }

//
//    @PutMapping("televisions/{id}")
//    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {
//
//        Optional<Television> television = televisionRepository.findById(id);
//
//        if (television.isEmpty()) {
//            throw new RecordNotFoundException("ID kon niet worden gevonden");
//        } else {
//            Television thisTelevision = television.get();
//            thisTelevision.setType(newTelevision.getType());
//            thisTelevision.setBrand(newTelevision.getBrand());
//            thisTelevision.setName(newTelevision.getName());
//            thisTelevision.setPrice(newTelevision.getPrice());
//            thisTelevision.setAvailableSizes(newTelevision.getAvailableSizes());
//            thisTelevision.setRefreshRate(newTelevision.getRefreshRate());
//            thisTelevision.setScreenType(newTelevision.getScreenType());
//            thisTelevision.setScreenQuality(newTelevision.getScreenQuality());
//            thisTelevision.setSmartTv(newTelevision.getSmartTv());
//            thisTelevision.setWifi(newTelevision.getWifi());
//            thisTelevision.setVoiceControl(newTelevision.getVoiceControl());
//            thisTelevision.setHdr(newTelevision.getHdr());
//            thisTelevision.setBluetooth(newTelevision.getBluetooth());
//            thisTelevision.setAmbiLight(newTelevision.getAmbiLight());
//            thisTelevision.setOriginalStock(newTelevision.getOriginalStock());
//            thisTelevision.setOriginalStockDate(newTelevision.getOriginalStockDate());
//            thisTelevision.setSold(newTelevision.getSold());
//            thisTelevision.setSoldDates(newTelevision.getSoldDates());
//
//            Television saveTelevision = televisionRepository.save(thisTelevision);
//
//            return ResponseEntity.ok().body(saveTelevision);
//        }
//    }
//
//    @DeleteMapping("/televisions/{id}")
//    public ResponseEntity<Television> deleteTelevision(@PathVariable Long id) {
//        televisionRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PatchMapping("/televisions/{id}")
//    public ResponseEntity<Television> updateTelevisionDetails(@PathVariable Long id, @RequestBody Television updatedTelevision) {
//
//        Optional<Television> television = televisionRepository.findById(id);
//
//        if (television.isEmpty()) {
//            throw new RecordNotFoundException("ID kon niet worden gevonden");
//        } else {
//            Television thisTelevision = television.get();
//            if (thisTelevision.getType() != null) {
//                thisTelevision.setType(updatedTelevision.getType());
//            }
//            if (thisTelevision.getBrand() != null) {
//                thisTelevision.setBrand(updatedTelevision.getBrand());
//            }
//            if (thisTelevision.getName() != null) {
//                thisTelevision.setName(updatedTelevision.getName());
//            }
//            if (thisTelevision.getPrice() != null) {
//                thisTelevision.setPrice(updatedTelevision.getPrice());
//            }
//            if (thisTelevision.getAvailableSizes() != null) {
//                thisTelevision.setAvailableSizes(updatedTelevision.getAvailableSizes());
//            }
//            if (thisTelevision.getRefreshRate() != null) {
//                thisTelevision.setRefreshRate(updatedTelevision.getRefreshRate());
//            }
//            if (thisTelevision.getScreenType() != null) {
//                thisTelevision.setScreenType(updatedTelevision.getScreenType());
//            }
//            if (thisTelevision.getScreenQuality() != null) {
//                thisTelevision.setScreenQuality(updatedTelevision.getScreenQuality());
//            }
//            if (thisTelevision.getSmartTv() != null) {
//                thisTelevision.setSmartTv(updatedTelevision.getSmartTv());
//            }
//            if (thisTelevision.getWifi() != null) {
//                thisTelevision.setWifi(updatedTelevision.getWifi());
//            }
//            if (thisTelevision.getVoiceControl() != null) {
//                thisTelevision.setVoiceControl(updatedTelevision.getVoiceControl());
//            }
//            if (thisTelevision.getHdr() != null) {
//                thisTelevision.setHdr(updatedTelevision.getHdr());
//            }
//            if (thisTelevision.getBluetooth() != null) {
//                thisTelevision.setBluetooth(updatedTelevision.getBluetooth());
//            }
//            if (thisTelevision.getAmbiLight() != null) {
//                thisTelevision.setAmbiLight(updatedTelevision.getAmbiLight());
//            }
//            if (thisTelevision.getOriginalStock() != null) {
//                thisTelevision.setOriginalStock(updatedTelevision.getOriginalStock());
//            }
//            if (thisTelevision.getOriginalStockDate() != null) {
//                thisTelevision.setOriginalStockDate(updatedTelevision.getOriginalStockDate());
//            }
//            if (thisTelevision.getSold() != null) {
//                thisTelevision.setSold(updatedTelevision.getSold());
//            }
//            if (thisTelevision.getSoldDates() != null) {
//                thisTelevision.setSoldDates(updatedTelevision.getSoldDates());
//            }
//
//                Television saveTelevision = televisionRepository.save(thisTelevision);
//
//                return ResponseEntity.ok().body(saveTelevision);
//            }
//        }
}


