package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BrandCreateRequestDto(
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
        String name,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Description must contain only letters")
        String description
) {
}
