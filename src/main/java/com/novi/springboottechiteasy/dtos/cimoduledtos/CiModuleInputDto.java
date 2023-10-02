package com.novi.springboottechiteasy.dtos.cimoduledtos;

import java.util.List;

public class CiModuleInputDto {
    // validaties later verder aanvullen en testen.

//    @NotNull(message = "Tv brand is required")
    private String brand;
//    @NotNull(message = "Tv type is required")
    private String type;
//    @Positive(message = "Tv price can't be 0")
    private Double price;
    private List<Long> compatibleTelevisionIds;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
