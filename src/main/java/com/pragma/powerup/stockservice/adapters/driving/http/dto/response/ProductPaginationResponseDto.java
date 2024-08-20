package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record ProductPaginationResponseDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String urlImage,
        Long quantity,
        Integer categoryId,
        Integer brandId,
        Integer userId
) {
}
