package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(
        Long idProduct,
        String name,
        BigDecimal price,
        String description,
        String urlImage,
        Long quantity,
        List<CategoryResponseDto> category,
        BrandResponseDto brand,
        Long userId
) {
    public Long idProduct() {
        return idProduct;
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
    public List<CategoryResponseDto> category() {
        return category;
    }

    @Override
    public BrandResponseDto brand() {
        return brand;
    }

    @Override
    public Long userId() {
        return userId;
    }
}
