package com.novi.springboottechiteasy.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Television {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String brand;
    private String name;

    private Double price;
    @OneToMany(mappedBy = "television")
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
    @Temporal(TemporalType.DATE)
    private Date originalStockDate;
    private Integer sold;
    @Temporal(TemporalType.DATE)
    @OneToMany(mappedBy = "television")
    private List<SoldDate> soldDates;

    public Television() {
    }

    public Television(
            Long id,
            String type,
            String brand,
            String name,
            Double price,
            List<ScreenSize> availableSizes,
            Double refreshRate,
            String screenType,
            String screenQuality,
            Boolean smartTv,
            Boolean wifi,
            Boolean voiceControl,
            Boolean hdr,
            Boolean bluetooth,
            Boolean ambiLight,
            Integer originalStock,
            Date originalStockDate,
            Integer sold,
            List<SoldDate> soldDates) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSizes = availableSizes;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.originalStockDate = originalStockDate;
        this.sold = sold;
        this.soldDates = soldDates;
    }

    public Long getId() {
        return id;
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

    public Double getRefreshRate() { return refreshRate; }

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
}