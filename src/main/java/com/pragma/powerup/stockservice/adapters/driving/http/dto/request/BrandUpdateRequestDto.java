package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

public record BrandUpdateRequestDto(
        Long id,
        String name,
        String description
) {
}
