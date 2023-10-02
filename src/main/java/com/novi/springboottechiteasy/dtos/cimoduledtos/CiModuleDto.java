package com.novi.springboottechiteasy.dtos.cimoduledtos;

import java.util.List;

public class CiModuleDto {
    private Long id;
    private String brand;
    private String type;
    private Double price;
    private List<Long> compatibleTelevisionIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
