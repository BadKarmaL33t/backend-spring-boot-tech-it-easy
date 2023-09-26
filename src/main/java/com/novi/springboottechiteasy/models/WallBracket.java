package com.novi.springboottechiteasy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WallBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;
    @ManyToMany(mappedBy = "compatibleWallBrackets", cascade = CascadeType.ALL)
    private List<Television> compatibleTelevisions;


    public Long getId() {
        return id;
    }

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

    public List<Television> getCompatibleTelevisions() {
        return compatibleTelevisions;
    }

    public void setCompatibleTelevisions(List<Television> compatibleTelevisions) {
        this.compatibleTelevisions = compatibleTelevisions;
    }
}