package com.novi.springboottechiteasy.controllers;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
