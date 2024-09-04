package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import java.math.BigDecimal;
import java.util.Set;

public record ProductCreateRequestDto(
         String name,
         BigDecimal price,
         String description,
         String urlImage,
         Long quantity,
         Set<Long> categoryIds,
         Long brandId,
         Long userId
) {
}
