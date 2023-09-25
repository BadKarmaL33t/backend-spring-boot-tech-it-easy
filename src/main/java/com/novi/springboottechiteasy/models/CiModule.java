package com.novi.springboottechiteasy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CiModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    private Double price;

    @OneToMany(mappedBy = "compatibleModule", cascade = CascadeType.ALL)
    private List<Television> compatibleTelevisions;


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

    public List<Television> getCompatibleTelevisions() {
        return compatibleTelevisions;
    }

    public void setCompatibleTelevisions(List<Television> compatibleTelevisions) {
        this.compatibleTelevisions = compatibleTelevisions;
    }
}