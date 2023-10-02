package com.novi.springboottechiteasy.models;

import jakarta.persistence.*;

@Entity
public class ScreenSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer availableSize;

    @ManyToOne
    @JoinColumn(name = "television_id")
    private Television television;


    public Integer getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(Integer availableSize) {
        this.availableSize = availableSize;
    }
}
