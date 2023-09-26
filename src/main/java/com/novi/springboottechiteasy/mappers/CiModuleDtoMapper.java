package com.novi.springboottechiteasy.mappers;

import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleDto;
import com.novi.springboottechiteasy.models.CiModule;
import com.novi.springboottechiteasy.models.Television;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CiModuleDtoMapper {
    public CiModuleDto mapToDto(CiModule ciModule) {
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
}



