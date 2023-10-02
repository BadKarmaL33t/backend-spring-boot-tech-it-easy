package com.novi.springboottechiteasy.mappers;

import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketDto;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.models.WallBracket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WallBracketDtoMapper {
    public WallBracketDto mapToDto(WallBracket wallBracket) {
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

}
