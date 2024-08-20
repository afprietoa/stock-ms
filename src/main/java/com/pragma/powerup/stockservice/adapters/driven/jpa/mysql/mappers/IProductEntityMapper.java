package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductEntityMapper {
    ProductEntity toProductEntity(Product product);
    Product toProduct(ProductEntity productEntity);
    ProductResponseDto toProductResponseDto(ProductEntity productEntity);
    ProductPaginationResponseDto toProductPaginationResponseDto(ProductEntity productEntity);
    ProductListResponseDto toProductListResponseDto(ProductEntity productEntity);
}
