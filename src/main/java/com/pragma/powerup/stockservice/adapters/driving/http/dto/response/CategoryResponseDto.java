package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record CategoryResponseDto(
        Long id,
        String name,
        String description
) {
    @Override
    public Long id() {
        return id;
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
