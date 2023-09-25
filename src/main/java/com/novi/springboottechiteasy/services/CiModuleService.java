package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleDto;
import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleInputDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.CiModule;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.CiModuleRepository;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiModuleService {
    private final CiModuleRepository ciModuleRepository;
    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;

    public CiModuleService(CiModuleRepository ciModuleRepository, TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
        this.televisionService = televisionService;
    }


    public List<CiModuleDto> getAllCiModules() {
        List<CiModule> ciModules = ciModuleRepository.findAll();
        List<CiModuleDto> ciModuleDtos = new ArrayList<>();

        for (CiModule ciModule : ciModules) {
            CiModuleDto dto = transferToDto(ciModule);
            ciModuleDtos.add(dto);
        }
        return ciModuleDtos;
    }

    public CiModuleDto getCiModuleById(Long id) {
        Optional<CiModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {
            CiModule foundModule = ciModule.get();

            return transferToDto(foundModule);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public CiModuleDto addCiModule(CiModuleInputDto inputDto) {
        CiModule ciModule = transferToCiModule(inputDto);
        ciModuleRepository.save(ciModule);
        return transferToDto(ciModule);
    }

    public CiModuleDto updateCiModule(Long id, CiModuleInputDto newCiModule) {
        Optional<CiModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {

            CiModule thisCiModule = ciModule.get();
            thisCiModule.setBrand(newCiModule.getBrand());
            thisCiModule.setType(newCiModule.getType());
            thisCiModule.setPrice(newCiModule.getPrice());

            CiModule saveCiModule = ciModuleRepository.save(thisCiModule);

            return transferToDto(saveCiModule);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public void deleteCiModule(Long id) {
        ciModuleRepository.deleteById(id);
    }

    public CiModuleDto updateCiModuleDetails(Long id, CiModuleInputDto updatedCiModule) {
        Optional<CiModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {

            CiModule thisCiModule = ciModule.get();
            if (updatedCiModule.getBrand() != null) {
                thisCiModule.setBrand(updatedCiModule.getBrand());
            }
            if (updatedCiModule.getType() != null) {
                thisCiModule.setType(updatedCiModule.getType());
            }
            if (updatedCiModule.getPrice() != null) {
                thisCiModule.setPrice(updatedCiModule.getPrice());
            }

            CiModule saveCiModule = ciModuleRepository.save(thisCiModule);

            return transferToDto(saveCiModule);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public CiModuleDto transferToDto(CiModule ciModule) {
        CiModuleDto dto = new CiModuleDto();

        dto.setId(ciModule.getId());
        dto.setBrand(ciModule.getBrand());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        if (ciModule.getCompatibleTelevisions() != null) {
            List<Long> compatibleTelevisionIds = ciModule.getCompatibleTelevisions()
                    .stream()
                    .map(Television::getId)
                    .collect(Collectors.toList());
            dto.setCompatibleTelevisionIds(compatibleTelevisionIds);
        }

        return dto;
    }

    public CiModule transferToCiModule(CiModuleInputDto inputDto) {
        CiModule ciModule = new CiModule();

        ciModule.setBrand(inputDto.getBrand());
        ciModule.setType(inputDto.getType());
        ciModule.setPrice(inputDto.getPrice());

        if (inputDto.getCompatibleTelevisionIds() != null) {
            List<Television> compatibleTelevisions = new ArrayList<>();
            for (Long televisionId : inputDto.getCompatibleTelevisionIds()) {
                Television television = televisionRepository.findById(televisionId)
                        .orElseThrow(() -> new RecordNotFoundException("No television found with id: " + televisionId));
                compatibleTelevisions.add(television);
            }
            ciModule.setCompatibleTelevisions(compatibleTelevisions);
        }

        return ciModule;
    }
}