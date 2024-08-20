package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record CategoryPaginationResponseDto(
        Long id,
        String name,
        String description
) {
}
