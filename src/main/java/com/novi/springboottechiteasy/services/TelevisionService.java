package com.novi.springboottechiteasy.services;

import com.novi.springboottechiteasy.dtos.cimoduledtos.CiModuleDto;
import com.novi.springboottechiteasy.dtos.remotecontrollerdtos.RemoteControllerDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionDto;
import com.novi.springboottechiteasy.dtos.televisiondtos.TelevisionInputDto;
import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketDto;
import com.novi.springboottechiteasy.exceptions.RecordNotFoundException;
import com.novi.springboottechiteasy.mappers.CiModuleDtoMapper;
import com.novi.springboottechiteasy.mappers.RemoteControllerDtoMapper;
import com.novi.springboottechiteasy.mappers.TelevisionDtoMapper;
import com.novi.springboottechiteasy.mappers.WallBracketDtoMapper;
import com.novi.springboottechiteasy.models.*;
import com.novi.springboottechiteasy.repositories.CiModuleRepository;
import com.novi.springboottechiteasy.repositories.RemoteControllerRepository;
import com.novi.springboottechiteasy.repositories.TelevisionRepository;
import com.novi.springboottechiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final TelevisionDtoMapper televisionDtoMapper;
    private final RemoteControllerRepository remoteControllerRepository;
    private final RemoteControllerDtoMapper remoteControllerDtoMapper;
    private final CiModuleRepository ciModuleRepository;
    private final CiModuleDtoMapper ciModuleDtoMapper;
    private final WallBracketRepository wallBracketRepository;
    private final WallBracketDtoMapper wallBracketDtoMapper;


    public TelevisionService(TelevisionRepository televisionRepository, TelevisionDtoMapper televisionDtoMapper, RemoteControllerRepository remoteControllerRepository, RemoteControllerDtoMapper remoteControllerDtoMapper, CiModuleRepository ciModuleRepository, CiModuleDtoMapper ciModuleDtoMapper, WallBracketRepository wallBracketRepository, WallBracketDtoMapper wallBracketDtoMapper) {
        this.televisionRepository = televisionRepository;
        this.televisionDtoMapper = televisionDtoMapper;
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerDtoMapper = remoteControllerDtoMapper;
        this.ciModuleRepository = ciModuleRepository;
        this.ciModuleDtoMapper = ciModuleDtoMapper;
        this.wallBracketRepository = wallBracketRepository;
        this.wallBracketDtoMapper = wallBracketDtoMapper;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionsDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = televisionDtoMapper.mapToDto(television);
            televisionsDtos.add(dto);
        }
        return televisionsDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television foundTv = television.get();

            return televisionDtoMapper.mapToDto(foundTv);
        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public RemoteControllerDto getCompatibleRemoteControllerByTelevisionId(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television foundTv = television.get();

            RemoteController remoteController = foundTv.getRemoteController();

            if (remoteController != null) {
                // mapToDto methode gebruiken om de RemoteController naar een RemoteControllerDto om te zetten
                return remoteControllerDtoMapper.mapToDto(remoteController);
            } else {
                throw new RecordNotFoundException("No compatible remotecontroller found for television with id: " + id);
            }
        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public CiModuleDto getCompatibleCiModuleByTelevisionId(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television foundTv = television.get();

            CiModule compatibleModule = foundTv.getCompatibleModule();

            if (compatibleModule != null) {
                // mapToDto methode gebruiken om de CiModule naar een CiModuleDto om te zetten
                return ciModuleDtoMapper.mapToDto(compatibleModule);
            } else {
                throw new RecordNotFoundException("No compatible module found for television with id: " + id);
            }
        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public List<WallBracketDto> getCompatibleWallBracketsByTelevisionId(Long id) {
        // Maak gebruik van Optional's ifPresentOrElse om beide ifs te reviewen
        return televisionRepository.findById(id)
                .map(foundTv -> {
                    List<WallBracket> compatibleWallBrackets = foundTv.getCompatibleWallBrackets();

                    if (compatibleWallBrackets != null && !compatibleWallBrackets.isEmpty()) {
                        // Gebruik streams om gevonden WallBrackets naar WallBracketDto te mappen met de DtoMapper
                        return compatibleWallBrackets.stream()
                                .map(wallBracketDtoMapper::mapToDto)
                                .collect(Collectors.toList());
                    } else {
                        throw new RecordNotFoundException("No compatible wall brackets found for television with id: " + id);
                    }
                })
                .orElseThrow(() -> new RecordNotFoundException("No television found with id: " + id));
    }

    public TelevisionDto addTelevision(TelevisionInputDto inputDto) {
        Television television = transferToTelevision(inputDto);
        televisionRepository.save(television);
        return televisionDtoMapper.mapToDto(television);
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

            return televisionDtoMapper.mapToDto(saveTelevision);

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

    public void assignWallBracketToTelevision(Long id, Long wallBracketId) {
        Optional<Television> television = televisionRepository.findById(id);
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(wallBracketId);

        if (television.isPresent() && wallBracket.isPresent()) {
            Television thisTelevision = television.get();
            WallBracket thisWallBracket = wallBracket.get();

            // Voeg de Television to aan de List compatibleTelevisions in de WallBracket
            thisWallBracket.getCompatibleTelevisions().add(thisTelevision);
            // Voeg de compatibleWallBracket toe aan de compatibleWallBrackets List van deze Television
            thisTelevision.getCompatibleWallBrackets().add(thisWallBracket);

            televisionRepository.save(thisTelevision);
            wallBracketRepository.save(thisWallBracket);
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

            return televisionDtoMapper.mapToDto(saveTelevision);

        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
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
