package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

public record CategoryUpdateRequestDto(
        Long id,
        String name,
        String description
) {
}
