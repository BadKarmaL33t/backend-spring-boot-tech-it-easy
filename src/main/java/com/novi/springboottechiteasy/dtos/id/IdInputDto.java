package com.novi.springboottechiteasy.dtos.id;

import jakarta.validation.constraints.NotNull;

public class IdInputDto {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}