package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record BrandPaginationResponseDto(
        Long id,
        String name,
        String description
) {
}
