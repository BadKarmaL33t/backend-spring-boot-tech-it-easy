package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleDto;
import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleInputDto;
import com.novi.springboottechiteasy.models.CiModule;
import com.novi.springboottechiteasy.services.CiModuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CiModuleController {
    private final CiModuleService ciModuleService;

    public CiModuleController(CiModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping(value = "/ci-modules")
    public ResponseEntity<List<CiModuleDto>> getAllCiModules() {
        List<CiModuleDto> ciModuleDtos;
        ciModuleDtos = ciModuleService.getAllCiModules();

        return ResponseEntity.ok().body(ciModuleDtos);
    }

    @GetMapping(value = "/ci-modules/{id}")
    public ResponseEntity<CiModuleDto> getCiModule(@PathVariable("id") Long id) {
        CiModuleDto ciModule = ciModuleService.getCiModuleById(id);

        return ResponseEntity.ok().body(ciModule);
    }

    @PostMapping("/ci-modules")
    public ResponseEntity<CiModuleDto> addCiModule(@Valid @RequestBody CiModuleInputDto ciModuleInputDto) {
        CiModuleDto dto = ciModuleService.addCiModule(ciModuleInputDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("ci-modules/{id}")
    public ResponseEntity<CiModuleDto> updateCiModule(@PathVariable Long id, @Valid @RequestBody CiModuleInputDto newCiModule) {
        CiModuleDto dto = ciModuleService.updateCiModule(id, newCiModule);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/ci-modules/{id}")
    public ResponseEntity<CiModule> deleteCiModule(@PathVariable Long id) {
        ciModuleService.deleteCiModule(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ci-modules/{id}")
    public ResponseEntity<CiModuleDto> updateCiModuleDetails(@PathVariable Long id, @Valid @RequestBody CiModuleInputDto updatedCiModule) {
        CiModuleDto dto = ciModuleService.updateCiModuleDetails(id, updatedCiModule);

        return ResponseEntity.ok().body(dto);
    }
}