package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductPaginationResponseDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String urlImage,
        Long quantity,
        List<CategoryResponseDto> category,
        BrandResponseDto brand,
        Long userId
) {
}
