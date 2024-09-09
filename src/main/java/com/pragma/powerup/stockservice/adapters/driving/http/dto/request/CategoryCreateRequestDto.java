package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequestDto(
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Description must contain only letters")
        String description
) {
}
