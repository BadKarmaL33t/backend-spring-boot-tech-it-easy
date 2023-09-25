package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionInputDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.models.CiModule;
import com.novi.springboottechiteasy.models.RemoteController;
import com.novi.springboottechiteasy.models.SoldDate;
import com.novi.springboottechiteasy.models.Television;
import com.novi.springboottechiteasy.repositories.CiModuleRepository;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CiModuleRepository ciModuleRepository;


    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CiModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
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
            if (newTelevision.getSoldDates() != null) {
                thisTelevision.getSoldDates().addAll(newTelevision.getSoldDates());
            }

            Television saveTelevision = televisionRepository.save(thisTelevision);

            return transferToDto(saveTelevision);

        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> television = televisionRepository.findById(id);
        Optional<RemoteController> remote = remoteControllerRepository.findById(remoteControllerId);

        if (television.isPresent() && remote.isPresent()) {
            Television thisTelevision = television.get();
            RemoteController thisRemote = remote.get();
            thisTelevision.setRemoteController(thisRemote);
            thisRemote.setTelevision(thisTelevision);

            televisionRepository.save(thisTelevision);
            remoteControllerRepository.save(thisRemote);

        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public void assignCiModuleToTelevision(Long id, Long ciModuleId) {
        Optional<Television> television = televisionRepository.findById(id);
        Optional<CiModule> module = ciModuleRepository.findById(ciModuleId);

        if (television.isPresent() && module.isPresent()) {
            Television thisTelevision = television.get();
            CiModule thisModule = module.get();

            // Voeg de Television to aan de List compatibleTelevisions in de CiModule
            thisModule.getCompatibleTelevisions().add(thisTelevision);
            // Voeg de compatibleModule toe aan de Television
            thisTelevision.setCompatibleModule(thisModule);

            televisionRepository.save(thisTelevision);
            ciModuleRepository.save(thisModule);
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
            if (updatedTelevision.getType() != null) {
                thisTelevision.setType(updatedTelevision.getType());
            }
            if (updatedTelevision.getBrand() != null) {
                thisTelevision.setBrand(updatedTelevision.getBrand());
            }
            if (updatedTelevision.getName() != null) {
                thisTelevision.setName(updatedTelevision.getName());
            }
            if (updatedTelevision.getPrice() != null) {
                thisTelevision.setPrice(updatedTelevision.getPrice());
            }
            if (updatedTelevision.getAvailableSizes() != null) {
                thisTelevision.setAvailableSizes(updatedTelevision.getAvailableSizes());
            }
            if (updatedTelevision.getRefreshRate() != null) {
                thisTelevision.setRefreshRate(updatedTelevision.getRefreshRate());
            }
            if (updatedTelevision.getScreenType() != null) {
                thisTelevision.setScreenType(updatedTelevision.getScreenType());
            }
            if (updatedTelevision.getScreenQuality() != null) {
                thisTelevision.setScreenQuality(updatedTelevision.getScreenQuality());
            }
            if (updatedTelevision.getSmartTv() != null) {
                thisTelevision.setSmartTv(updatedTelevision.getSmartTv());
            }
            if (updatedTelevision.getWifi() != null) {
                thisTelevision.setWifi(updatedTelevision.getWifi());
            }
            if (updatedTelevision.getVoiceControl() != null) {
                thisTelevision.setVoiceControl(updatedTelevision.getVoiceControl());
            }
            if (updatedTelevision.getHdr() != null) {
                thisTelevision.setHdr(updatedTelevision.getHdr());
            }
            if (updatedTelevision.getBluetooth() != null) {
                thisTelevision.setBluetooth(updatedTelevision.getBluetooth());
            }
            if (updatedTelevision.getAmbiLight() != null) {
                thisTelevision.setAmbiLight(updatedTelevision.getAmbiLight());
            }
            if (updatedTelevision.getOriginalStock() != null) {
                thisTelevision.setOriginalStock(updatedTelevision.getOriginalStock());
            }
            if (updatedTelevision.getOriginalStockDate() != null) {
                thisTelevision.setOriginalStockDate(updatedTelevision.getOriginalStockDate());
            }
            if (updatedTelevision.getSold() != null) {
                thisTelevision.setSold(updatedTelevision.getSold());
            }
            if (updatedTelevision.getSoldDates() != null) {
                for (SoldDate date : updatedTelevision.getSoldDates()) {
                    SoldDate soldDate = new SoldDate();
                    soldDate.setSoldDate(date.getSoldDate());
                    soldDate.setTelevision(thisTelevision);
                    thisTelevision.getSoldDates().add(soldDate);
                }
                televisionRepository.save(thisTelevision);
            }
            if (updatedTelevision.getRemoteControllerId() != null) {
                RemoteController remoteController = remoteControllerRepository.findById(updatedTelevision.getRemoteControllerId())
                        .orElseThrow(() -> new RecordNotFoundException("No remote-controller found with id: " + updatedTelevision.getRemoteControllerId()));

                thisTelevision.setRemoteController(remoteController);
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

        if (television.getRemoteController() != null) {
            dto.setRemoteControllerId(television.getRemoteController().getId()); // De RemoteController Id gebruiken om de remote in de Television te plaatsen
        }
        if (television.getCompatibleModule() != null) {
            dto.setCompatibleModuleId(television.getCompatibleModule().getId()); // De CiModule Id gebruiken om de remote in de Television te plaatsen
        }

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

        if (inputDto.getRemoteControllerId() != null) {
            RemoteController remoteController = remoteControllerRepository.findById(inputDto.getRemoteControllerId())
                    .orElseThrow(() -> new RecordNotFoundException("No remote-controller found with id: " + inputDto.getRemoteControllerId()));
            tv.setRemoteController(remoteController);
        }
        if (inputDto.getCompatibleModuleId() != null) {
            CiModule ciModule = ciModuleRepository.findById(inputDto.getCompatibleModuleId())
                    .orElseThrow(() -> new RecordNotFoundException("No CiModule found with id: " + inputDto.getCompatibleModuleId()));
            tv.setCompatibleModule(ciModule);
        }

        return tv;
    }
}
