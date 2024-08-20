package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;

public record ProductResponseDto(
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
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String urlImage() {
        return urlImage;
    }

    @Override
    public Long quantity() {
        return quantity;
    }

    @Override
    public Integer categoryId() {
        return categoryId;
    }

    @Override
    public Integer brandId() {
        return brandId;
    }

    @Override
    public Integer userId() {
        return userId;
    }
}
