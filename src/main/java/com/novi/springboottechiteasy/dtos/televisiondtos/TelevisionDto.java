package com.novi.springboottechiteasy.dtos.televisiondtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novi.springboottechiteasy.dtos.wallbracketdtos.WallBracketDto;
import com.novi.springboottechiteasy.models.*;

import java.util.Date;
import java.util.List;

public class TelevisionDto {

    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private List<ScreenSize> availableSizes;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Date originalStockDate;
    private Integer sold;
    private List<SoldDate> soldDates;

    @JsonProperty("remoteControllerId")
    private Long remoteControllerId;

    private Long compatibleModuleId;
    private List<WallBracketDto> wallBrackets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ScreenSize> getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(List<ScreenSize> availableSizes) {
        this.availableSizes = availableSizes;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public Boolean getSmartTv() {
        return smartTv;
    }

    public void setSmartTv(Boolean smartTv) {
        this.smartTv = smartTv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getVoiceControl() {
        return voiceControl;
    }

    public void setVoiceControl(Boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Boolean getAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(Boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public Date getOriginalStockDate() {
        return originalStockDate;
    }

    public void setOriginalStockDate(Date originalStockDate) {
        this.originalStockDate = originalStockDate;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public List<SoldDate> getSoldDates() {
        return soldDates;
    }

    public void setSoldDates(List<SoldDate> soldDates) {
        this.soldDates = soldDates;
    }

    public Long getRemoteControllerId() {
        return remoteControllerId;
    }

    public void setRemoteControllerId(Long remoteControllerId) {
        this.remoteControllerId = remoteControllerId;
    }

    public Long getCompatibleModuleId() {
        return compatibleModuleId;
    }

    public void setCompatibleModuleId(Long compatibleModuleId) {
        this.compatibleModuleId = compatibleModuleId;
    }

    public List<WallBracketDto> getWallBrackets() {
        return wallBrackets;
    }

    public void setWallBrackets(List<WallBracketDto> wallBrackets) {
        this.wallBrackets = wallBrackets;
    }
}
