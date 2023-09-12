package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerInputDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public RemoteControllerDto getRemoteControllerById(Long id) {
        Optional<RemoteController> remote = remoteControllerRepository.findById(id);

        if (remote.isPresent()) {
            RemoteController foundRemote = remote.get();

            return transferToDto(foundRemote);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto inputDto) {
        RemoteController remote = transferToRemoteControl(inputDto);
        remoteControllerRepository.save(remote);
        return transferToDto(remote);
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto newRemote) {
        Optional<RemoteController> remote = remoteControllerRepository.findById(id);

        if (remote.isPresent()) {

            RemoteController thisRemote = remote.get();
            thisRemote.setCompatibleWith(newRemote.getCompatibleWith());
            thisRemote.setBatteryType(newRemote.getBatteryType());
            thisRemote.setName(newRemote.getName());
            thisRemote.setPrice(newRemote.getPrice());
            thisRemote.setOriginalStock(newRemote.getOriginalStock());
            thisRemote.setTelevision(newRemote.getTelevision());

            RemoteController saveRemote = remoteControllerRepository.save(thisRemote);

            return transferToDto(saveRemote);

        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public void deleteRemoteController(Long id) {
        remoteControllerRepository.deleteById(id);
    }



    public RemoteControllerDto updateRemoteControllerDetails(Long id, RemoteControllerInputDto updatedRemoteController) {
        Optional<RemoteController> remote = remoteControllerRepository.findById(id);

        if (remote.isPresent()) {

            RemoteController thisRemote = remote.get();
            if (thisRemote.getCompatibleWith() != null) {
                thisRemote.setCompatibleWith(updatedRemoteController.getCompatibleWith());
            }
            if (thisRemote.getBatteryType() != null) {
                thisRemote.setBatteryType(updatedRemoteController.getBatteryType());
            }
            if (thisRemote.getName() != null) {
                thisRemote.setName(updatedRemoteController.getName());
            }
            if (thisRemote.getPrice() != null) {
                thisRemote.setPrice(updatedRemoteController.getPrice());
            }
            if (thisRemote.getOriginalStock() != null) {
                thisRemote.setOriginalStock(updatedRemoteController.getOriginalStock());
            }
            if (thisRemote.getTelevision() != null) {
                thisRemote.setTelevision(updatedRemoteController.getTelevision());
            }

            RemoteController saveRemote = remoteControllerRepository.save(thisRemote);

            return transferToDto(saveRemote);

        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
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