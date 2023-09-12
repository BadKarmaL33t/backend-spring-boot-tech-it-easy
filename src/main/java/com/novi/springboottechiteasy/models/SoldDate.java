package com.novi.springboottechiteasy.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SoldDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date soldDate;

    @ManyToOne
    @JoinColumn(name = "television_id")
    private Television television;

    
    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }
}
