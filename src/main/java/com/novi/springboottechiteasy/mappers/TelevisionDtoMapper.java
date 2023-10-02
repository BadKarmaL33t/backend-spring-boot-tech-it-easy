package com.novi.springboottechiteasy.mappers;

import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.models.Television;
import org.springframework.stereotype.Component;

@Component
public class TelevisionDtoMapper {
    public TelevisionDto mapToDto(Television television) {
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

        if (television.getRemoteController() != null) {
            dto.setRemoteControllerId(television.getRemoteController().getId()); // De RemoteController Id gebruiken om de remote in de Television te plaatsen
        }
        if (television.getCompatibleModule() != null) {
            dto.setCompatibleModuleId(television.getCompatibleModule().getId()); // De CiModule Id gebruiken om de remote in de Television te plaatsen
        }

        return dto;
    }
}
