package com.novi.springboottechiteasy.mappers;

import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.models.RemoteController;
import org.springframework.stereotype.Component;

@Component
public class RemoteControllerDtoMapper {
    public RemoteControllerDto mapToDto(RemoteController remote) {
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
}
