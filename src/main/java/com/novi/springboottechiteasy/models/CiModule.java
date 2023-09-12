package com.novi.springboottechiteasy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class CiModule {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String type;
    private Double price;

    @OneToMany(mappedBy = "module")
    private List<Television> compatibleTelevisions;

//    public CiModule() {
//    }
//
//    public CiModule(
//            Long id,
//            String brand,
//            String type,
//            Double price) {
//
//        this.id = id;
//        this.brand = brand;
//        this.type = type;
//        this.price = price;
//    }

    public Long getId() {
        return id;
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
}