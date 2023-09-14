package com.novi.springboottechiteasy.dtos.remotecontrollerdtos;

import com.novi.springboottechiteasy.models.Television;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class RemoteControllerInputDto {
    // validaties later verder aanvullen en testen.

    private String compatibleWith;
    private String batteryType;
    @NotNull(message = "wall bracket name is required")
    private String name;
    @Positive(message = "wall bracket price can't be 0")
    private Double price;
    @PositiveOrZero(message = "Initial stock can't have a negative value")
    private Integer originalStock;
    private Long televisionId;


    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
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

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public Long getTelevisionId() {
        return televisionId;
    }

    public void setTelevisionId(Long televisionId) {
        this.televisionId = televisionId;
    }
}
