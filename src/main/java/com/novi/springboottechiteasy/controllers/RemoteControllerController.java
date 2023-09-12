package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerInputDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionInputDto;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.services.RemoteControllerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping(value = "/remote-controllers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        List<RemoteControllerDto> remoteControllerDtos;
        remoteControllerDtos = remoteControllerService.getAllRemoteControllers();

        return ResponseEntity.ok().body(remoteControllerDtos);
    }

    @GetMapping(value = "/remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControl(@PathVariable("id") Long id) {
        RemoteControllerDto remote = remoteControllerService.getRemoteControllerById(id);

        return ResponseEntity.ok().body(remote);
    }

    @PostMapping("/remote-controllers")
    public ResponseEntity<RemoteControllerDto> addRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto dto = remoteControllerService.addRemoteController(remoteControllerInputDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@PathVariable Long id, @Valid @RequestBody RemoteControllerInputDto newRemoteController) {
        RemoteControllerDto dto = remoteControllerService.updateRemoteController(id, newRemoteController);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/remote-controllers/{id}")
    public ResponseEntity<RemoteController> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteControllerDetails(@PathVariable Long id, @Valid @RequestBody RemoteControllerInputDto updatedRemoteController) {
        RemoteControllerDto dto = remoteControllerService.updateRemoteControllerDetails(id, updatedRemoteController);

        return ResponseEntity.ok().body(dto);
    }
}

