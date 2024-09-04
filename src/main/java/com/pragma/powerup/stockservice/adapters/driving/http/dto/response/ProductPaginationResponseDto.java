package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;
import java.util.Set;

public record ProductPaginationResponseDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String urlImage,
        Long quantity,
        Set<CategoryResponseDto> category,
        BrandResponseDto brand,
        Long userId
) {
}
