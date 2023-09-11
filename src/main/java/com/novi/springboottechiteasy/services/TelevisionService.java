package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.TelevisionInputDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionsDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = transferToDto(television);
            televisionsDtos.add(dto);
        }
        return televisionsDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television foundTv = television.get();

            return transferToDto(foundTv);
        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public TelevisionDto addTelevision(TelevisionInputDto inputDto) {
    Television television = transferToTelevision(inputDto);
    televisionRepository.save(television);
        return transferToDto(television);
    }

    public TelevisionDto transferToDto(Television television) {
        TelevisionDto dto = new TelevisionDto();

        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSizes(television.getAvailableSizes());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setOriginalStockDate(television.getOriginalStockDate());
        dto.setSold(television.getSold());
        dto.setSoldDates(television.getSoldDates());

        return dto;
    }

    public Television transferToTelevision(TelevisionInputDto inputDto) {
        Television tv = new Television();

        tv.setType(inputDto.getType());
        tv.setBrand(inputDto.getBrand());
        tv.setName(inputDto.getName());
        tv.setPrice(inputDto.getPrice());
        tv.setAvailableSizes(inputDto.getAvailableSizes());
        tv.setRefreshRate(inputDto.getRefreshRate());
        tv.setScreenType(inputDto.getScreenType());
        tv.setScreenQuality(inputDto.getScreenQuality());
        tv.setSmartTv(inputDto.getSmartTv());
        tv.setWifi(inputDto.getWifi());
        tv.setVoiceControl(inputDto.getVoiceControl());
        tv.setHdr(inputDto.getHdr());
        tv.setBluetooth(inputDto.getBluetooth());
        tv.setAmbiLight(inputDto.getAmbiLight());
        tv.setOriginalStock(inputDto.getOriginalStock());
        tv.setOriginalStockDate(inputDto.getOriginalStockDate());
        tv.setSold(inputDto.getSold());
        tv.setSoldDates(inputDto.getSoldDates());

        return tv;
    }
}
