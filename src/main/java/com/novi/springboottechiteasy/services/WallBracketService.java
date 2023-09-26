package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketDto;
import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketInputDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.models.WallBracket;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import com.novi.springboottechiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;
    private final TelevisionRepository televisionRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository, TelevisionRepository televisionRepository) {
        this.wallBracketRepository = wallBracketRepository;
        this.televisionRepository = televisionRepository;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();

        for (WallBracket wallBracket : wallBrackets) {
            WallBracketDto dto = transferToDto(wallBracket);
            wallBracketDtos.add(dto);
        }
        return wallBracketDtos;
    }

    public WallBracketDto getWallBracketById(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            WallBracket foundWallBracket = wallBracket.get();

            return transferToDto(foundWallBracket);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public WallBracketDto addWallBracket(WallBracketInputDto inputDto) {
        WallBracket wallBracket = transferToWallBracket(inputDto);
        wallBracketRepository.save(wallBracket);
        return transferToDto(wallBracket);
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto newWallBracket) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {

            WallBracket thisWallBracket = wallBracket.get();
            thisWallBracket.setSize(newWallBracket.getSize());
            thisWallBracket.setAdjustable(newWallBracket.getAdjustable());
            thisWallBracket.setName(newWallBracket.getName());
            thisWallBracket.setPrice(newWallBracket.getPrice());

            WallBracket saveWallBracket = wallBracketRepository.save(thisWallBracket);

            return transferToDto(saveWallBracket);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public void deleteWallBracket(Long id) {
        wallBracketRepository.deleteById(id);
    }

    public WallBracketDto updateWallBracketDetails(Long id, WallBracketInputDto updatedWallBracket) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {

            WallBracket thisWallBracket = wallBracket.get();
            if (updatedWallBracket.getSize() != null) {
                thisWallBracket.setSize(updatedWallBracket.getSize());
            }
            if (updatedWallBracket.getAdjustable() != null) {
                thisWallBracket.setAdjustable(updatedWallBracket.getAdjustable());
            }
            if (updatedWallBracket.getName() != null) {
                thisWallBracket.setName(updatedWallBracket.getName());
            }
            if (updatedWallBracket.getPrice() != null) {
                thisWallBracket.setPrice(updatedWallBracket.getPrice());
            }

            WallBracket saveWallBracket = wallBracketRepository.save(thisWallBracket);

            return transferToDto(saveWallBracket);
        } else {
            throw new RecordNotFoundException("No remote-controller found with id: " + id);
        }
    }

    public WallBracketDto transferToDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setId(wallBracket.getId());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());

        if (wallBracket.getCompatibleTelevisions() != null) {
            List<Long> compatibleTelevisionIds = wallBracket.getCompatibleTelevisions()
                    .stream()
                    .map(Television::getId)
                    .collect(Collectors.toList());
            dto.setCompatibleTelevisionIds(compatibleTelevisionIds);
        }

        return dto;
    }

    public WallBracket transferToWallBracket(WallBracketInputDto inputDto) {
        WallBracket wallBracket = new WallBracket();

        wallBracket.setSize(inputDto.getSize());
        wallBracket.setAdjustable(inputDto.getAdjustable());
        wallBracket.setName(inputDto.getName());
        wallBracket.setPrice(inputDto.getPrice());

        if (inputDto.getCompatibleTelevisionIds() != null) {
            List<Television> compatibleTelevisions = new ArrayList<>();
            for (Long televisionId : inputDto.getCompatibleTelevisionIds()) {
                Television television = televisionRepository.findById(televisionId)
                        .orElseThrow(() -> new RecordNotFoundException("No television found with id: " + televisionId));
                compatibleTelevisions.add(television);
            }
            wallBracket.setCompatibleTelevisions(compatibleTelevisions);
        }

        return wallBracket;
    }
}
