package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionInputDto;
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

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {

            Television thisTelevision = television.get();
            thisTelevision.setType(newTelevision.getType());
            thisTelevision.setBrand(newTelevision.getBrand());
            thisTelevision.setName(newTelevision.getName());
            thisTelevision.setPrice(newTelevision.getPrice());
            thisTelevision.setAvailableSizes(newTelevision.getAvailableSizes());
            thisTelevision.setRefreshRate(newTelevision.getRefreshRate());
            thisTelevision.setScreenType(newTelevision.getScreenType());
            thisTelevision.setScreenQuality(newTelevision.getScreenQuality());
            thisTelevision.setSmartTv(newTelevision.getSmartTv());
            thisTelevision.setWifi(newTelevision.getWifi());
            thisTelevision.setVoiceControl(newTelevision.getVoiceControl());
            thisTelevision.setHdr(newTelevision.getHdr());
            thisTelevision.setBluetooth(newTelevision.getBluetooth());
            thisTelevision.setAmbiLight(newTelevision.getAmbiLight());
            thisTelevision.setOriginalStock(newTelevision.getOriginalStock());
            thisTelevision.setOriginalStockDate(newTelevision.getOriginalStockDate());
            thisTelevision.setSold(newTelevision.getSold());
            thisTelevision.setSoldDates(newTelevision.getSoldDates());

            Television saveTelevision = televisionRepository.save(thisTelevision);

            return transferToDto(saveTelevision);

        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public void deleteTelevision(Long id) {
        televisionRepository.deleteById(id);
    }

    public TelevisionDto updateTelevisionDetails(Long id, TelevisionInputDto updatedTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {

            Television thisTelevision = television.get();
            if (thisTelevision.getType() != null) {
                thisTelevision.setType(updatedTelevision.getType());
            }
            if (thisTelevision.getBrand() != null) {
                thisTelevision.setBrand(updatedTelevision.getBrand());
            }
            if (thisTelevision.getName() != null) {
                thisTelevision.setName(updatedTelevision.getName());
            }
            if (thisTelevision.getPrice() != null) {
                thisTelevision.setPrice(updatedTelevision.getPrice());
            }
            if (thisTelevision.getAvailableSizes() != null) {
                thisTelevision.setAvailableSizes(updatedTelevision.getAvailableSizes());
            }
            if (thisTelevision.getRefreshRate() != null) {
                thisTelevision.setRefreshRate(updatedTelevision.getRefreshRate());
            }
            if (thisTelevision.getScreenType() != null) {
                thisTelevision.setScreenType(updatedTelevision.getScreenType());
            }
            if (thisTelevision.getScreenQuality() != null) {
                thisTelevision.setScreenQuality(updatedTelevision.getScreenQuality());
            }
            if (thisTelevision.getSmartTv() != null) {
                thisTelevision.setSmartTv(updatedTelevision.getSmartTv());
            }
            if (thisTelevision.getWifi() != null) {
                thisTelevision.setWifi(updatedTelevision.getWifi());
            }
            if (thisTelevision.getVoiceControl() != null) {
                thisTelevision.setVoiceControl(updatedTelevision.getVoiceControl());
            }
            if (thisTelevision.getHdr() != null) {
                thisTelevision.setHdr(updatedTelevision.getHdr());
            }
            if (thisTelevision.getBluetooth() != null) {
                thisTelevision.setBluetooth(updatedTelevision.getBluetooth());
            }
            if (thisTelevision.getAmbiLight() != null) {
                thisTelevision.setAmbiLight(updatedTelevision.getAmbiLight());
            }
            if (thisTelevision.getOriginalStock() != null) {
                thisTelevision.setOriginalStock(updatedTelevision.getOriginalStock());
            }
            if (thisTelevision.getOriginalStockDate() != null) {
                thisTelevision.setOriginalStockDate(updatedTelevision.getOriginalStockDate());
            }
            if (thisTelevision.getSold() != null) {
                thisTelevision.setSold(updatedTelevision.getSold());
            }
            if (thisTelevision.getSoldDates() != null) {
                thisTelevision.setSoldDates(updatedTelevision.getSoldDates());
            }

            Television saveTelevision = televisionRepository.save(thisTelevision);

            return transferToDto(saveTelevision);

        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
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
        Television tv = new Television(remoteController, module, wallBrackets);

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
