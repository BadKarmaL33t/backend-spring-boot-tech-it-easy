package com.novi.springboottechiteasy.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "television", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SoldDate> soldDates;
    @OneToOne
    @JoinColumn(name = "remote_controller_id", nullable = false)
    private RemoteController remoteController;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private CiModule compatibleModule;
    @ManyToMany
    @JoinTable(
            name = "television_brackets",
            joinColumns = @JoinColumn(name = "bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "television_id")
    )
    List<WallBracket> wallBrackets;


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

    public RemoteController getRemoteController() {return remoteController;}

    public void setRemoteController(RemoteController remoteController) {this.remoteController = remoteController;}

    public CiModule getCompatibleModule() {
        return compatibleModule;
    }

    public void setCompatibleModule(CiModule compatibleModule) {
        this.compatibleModule = compatibleModule;
    }

    public List<WallBracket> getWallBrackets() {return wallBrackets;}

    public void setWallBrackets(List<WallBracket> wallBrackets) {this.wallBrackets = wallBrackets;}
}