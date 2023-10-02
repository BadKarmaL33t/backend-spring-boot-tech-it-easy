package com.novi.springboottechiteasy.dtos.wallbracketdtos;

import com.novi.springboottechiteasy.models.Television;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class WallBracketInputDto {
    // validaties later verder aanvullen en testen.

    private String size;
    private Boolean adjustable;
//    @NotNull(message = "wall bracket name is required")
    private String name;
//    @Positive(message = "wall bracket price can't be 0")
    private Double price;
    private List<Long> compatibleTelevisionIds;


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
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

    public List<Long> getCompatibleTelevisionIds() {
        return compatibleTelevisionIds;
    }

    public void setCompatibleTelevisionIds(List<Long> compatibleTelevisionIds) {
        this.compatibleTelevisionIds = compatibleTelevisionIds;
    }
}
