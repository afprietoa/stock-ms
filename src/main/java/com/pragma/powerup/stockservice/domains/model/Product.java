package com.pragma.powerup.stockservice.domains.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Product {
    private Long idProduct;
    private String name;
    private BigDecimal price;
    private String description;
    private String urlImage;
    private BigDecimal quantity;
    private List<Long> categoryIds;
    private Long brandId;
    private  Long userId;

    public Product() {
    }

    public Product(Long userId, Long brandId, BigDecimal quantity, String description, String name, BigDecimal price, String urlImage, List<Long> categoryIds) {

        this.userId = userId;
        this.brandId = brandId;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.categoryIds = categoryIds;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
