package com.novi.springboottechiteasy.dtos.televisiondtos;

import com.novi.springboottechiteasy.models.*;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public class TelevisionInputDto {
    // id is geen input door generatedValue, dus die hoort er niet bij!

    // validaties later verder aanvullen en testen.

    @NotNull(message = "Tv type is required")
    private String type;
    @NotNull(message = "Tv brand is required")
    private String brand;
    @Size(max = 20, message = "Tv names must stay between 0-20 characters")
    private String name;
    @Positive(message = "Tv price can't be 0")
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
    @PositiveOrZero(message = "Initial stock can't have a negative value")
    private Integer originalStock;
    private Date originalStockDate;
    @PositiveOrZero(message = "Amount sold can't have a negative value")
    private Integer sold;
    private List<SoldDate> soldDates;
    private Long remoteControllerId;
    private CiModule module;
    private List<WallBracket> wallBrackets;


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

    public CiModule getModule() {return module;}

    public void setModule(CiModule module) {this.module = module;}

    public List<WallBracket> getWallBrackets() {return wallBrackets;}

    public void setWallBrackets(List<WallBracket> wallBrackets) {this.wallBrackets = wallBrackets;}
}