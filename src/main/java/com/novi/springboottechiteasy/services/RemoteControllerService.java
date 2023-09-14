package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerInputDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Lazy
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;
    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;
    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository, TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.remoteControllerRepository = remoteControllerRepository;
        this.televisionService = televisionService;
        this.televisionRepository = televisionRepository;
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
            if (updatedRemoteController.getTelevisionId() != null) {
                Long televisionId = updatedRemoteController.getTelevisionId();
                Television television = televisionRepository.findById(televisionId)
                        .orElseThrow(() -> new RecordNotFoundException("No television found with id: " + televisionId));

                // Manually map the fields from TelevisionDto to Television entity
                thisRemote.setTelevision(television);
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

        if (remote.getTelevision() != null) {
            dto.setTelevisionId(remote.getTelevision().getId()); // De Television Id gebruiken om de televisie in de RemoteController te plaatsen
        }

        return dto;
    }

    public RemoteController transferToRemoteControl(RemoteControllerInputDto inputDto) {
        RemoteController remote = new RemoteController();

        remote.setCompatibleWith(inputDto.getCompatibleWith());
        remote.setBatteryType(inputDto.getBatteryType());
        remote.setName(inputDto.getName());
        remote.setPrice(inputDto.getPrice());
        remote.setOriginalStock(inputDto.getOriginalStock());

        if (inputDto.getTelevisionId() != null) {
            TelevisionDto televisionDto = televisionService.getTelevisionById(inputDto.getTelevisionId());
            if (televisionDto != null) {
                Television television = new Television();
                // als er een tv gevonden wordt met deze id, map deze dan naar de Television,
                // zodat de remote deze television via de setter kan toewijzen
                remote.setTelevision(television);
            } else {
                throw new RecordNotFoundException("No television found with id: " + inputDto.getTelevisionId());
            }
        }

        return remote;
    }
}