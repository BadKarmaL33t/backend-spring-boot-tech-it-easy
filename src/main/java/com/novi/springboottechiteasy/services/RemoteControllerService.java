package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerInputDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.mappers.RemoteControllerDtoMapper;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;
    private final RemoteControllerDtoMapper remoteControllerDtoMapper;
    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository, RemoteControllerDtoMapper remoteControllerDtoMapper, TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerDtoMapper = remoteControllerDtoMapper;
        this.televisionService = televisionService;
        this.televisionRepository = televisionRepository;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto dto = remoteControllerDtoMapper.mapToDto(remoteController);
            remoteControllerDtos.add(dto);
        }
        return remoteControllerDtos;
    }

    public RemoteControllerDto getRemoteControllerById(Long id) {
        Optional<RemoteController> remote = remoteControllerRepository.findById(id);

        if (remote.isPresent()) {
            RemoteController foundRemote = remote.get();

            return remoteControllerDtoMapper.mapToDto(foundRemote);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto inputDto) {
        RemoteController remote = transferToRemoteControl(inputDto);
        remoteControllerRepository.save(remote);
        return remoteControllerDtoMapper.mapToDto(remote);
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

            return remoteControllerDtoMapper.mapToDto(saveRemote);

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
            if (updatedRemoteController.getCompatibleWith() != null) {
                thisRemote.setCompatibleWith(updatedRemoteController.getCompatibleWith());
            }
            if (updatedRemoteController.getBatteryType() != null) {
                thisRemote.setBatteryType(updatedRemoteController.getBatteryType());
            }
            if (updatedRemoteController.getName() != null) {
                thisRemote.setName(updatedRemoteController.getName());
            }
            if (updatedRemoteController.getPrice() != null) {
                thisRemote.setPrice(updatedRemoteController.getPrice());
            }
            if (updatedRemoteController.getOriginalStock() != null) {
                thisRemote.setOriginalStock(updatedRemoteController.getOriginalStock());
            }
            if (updatedRemoteController.getTelevisionId() != null) {
                Television television = televisionRepository.findById(updatedRemoteController.getTelevisionId())
                        .orElseThrow(() -> new RecordNotFoundException("No television found with id: " + updatedRemoteController.getTelevisionId()));

                thisRemote.setTelevision(television);
            }

            RemoteController saveRemote = remoteControllerRepository.save(thisRemote);

            return remoteControllerDtoMapper.mapToDto(saveRemote);

        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
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