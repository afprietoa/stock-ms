package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import java.math.BigDecimal;

public record ProductUpdateRequestDto(
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
