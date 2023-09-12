package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerInputDto;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto dto = transferToDto(remoteController);
            remoteControllerDtos.add(dto);
        }
        return remoteControllerDtos;
    }

    public RemoteControllerDto transferToDto(RemoteController remote) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setId(remote.getId());
        dto.setCompatibleWith(remote.getCompatibleWith());
        dto.setBatteryType(remote.getBatteryType());
        dto.setName(remote.getName());
        dto.setPrice(remote.getPrice());
        dto.setOriginalStock(remote.getOriginalStock());
        dto.setTelevision(remote.getTelevision());

        return dto;
    }

    public RemoteController transferToRemoteControl(RemoteControllerInputDto inputDto) {
        RemoteController remote = new RemoteController();

        remote.setCompatibleWith(inputDto.getCompatibleWith());
        remote.setBatteryType(inputDto.getBatteryType());
        remote.setName(inputDto.getName());
        remote.setPrice(inputDto.getPrice());
        remote.setOriginalStock(inputDto.getOriginalStock());
        remote.setTelevision(inputDto.getTelevision());

        return remote;
    }
}
