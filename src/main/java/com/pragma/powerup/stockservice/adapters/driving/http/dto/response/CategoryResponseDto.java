package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record CategoryResponseDto(
        Long idCategory,
        String name,
        String description
) {


    @Override
    public Long idCategory() {
        return idCategory;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }
}
