package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketDto;
import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketInputDto;
import com.novi.springboottechiteasy.models.WallBracket;
import com.novi.springboottechiteasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class WallBrackController {
    private final WallBracketService wallBracketService;

    public WallBrackController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping(value = "/wall-brackets")
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        List<WallBracketDto> wallBracketDtos;
        wallBracketDtos = wallBracketService.getAllWallBrackets();

        return ResponseEntity.ok().body(wallBracketDtos);
    }

    @GetMapping(value = "/wall-brackets/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable("id") Long id) {
        WallBracketDto wallBracket = wallBracketService.getWallBracketById(id);

        return ResponseEntity.ok().body(wallBracket);
    }

    @PostMapping("/wall-brackets")
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto dto = wallBracketService.addWallBracket(wallBracketInputDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("wall-brackets/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketInputDto newWallBracket) {
        WallBracketDto dto = wallBracketService.updateWallBracket(id, newWallBracket);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/wall-brackets/{id}")
    public ResponseEntity<WallBracket> deleteWallBracket(@PathVariable Long id) {
        wallBracketService.deleteWallBracket(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/wall-brackets/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracketDetails(@PathVariable Long id, @RequestBody WallBracketInputDto updatedWallBracket) {
        WallBracketDto dto = wallBracketService.updateWallBracketDetails(id, updatedWallBracket);

        return ResponseEntity.ok().body(dto);
    }
}
