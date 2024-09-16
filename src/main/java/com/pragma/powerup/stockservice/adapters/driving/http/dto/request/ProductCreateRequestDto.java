package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record ProductCreateRequestDto(
        @NotBlank
         String name,
        @NotNull
        @Positive
         BigDecimal price,
        @NotBlank
         String description,
         String urlImage,
        @NotNull
        @Positive
        BigDecimal quantity,
        @Size(min = 1, max = 3)
        List<Long> categoryIds,
        @NotNull
         Long brandId,
         Long userId
) {
}
