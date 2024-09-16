package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

public record BrandResponseDto(
        Long idBrand,
        String name,
        String description
) {
    public Long idBrand() {
        return idBrand;
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
