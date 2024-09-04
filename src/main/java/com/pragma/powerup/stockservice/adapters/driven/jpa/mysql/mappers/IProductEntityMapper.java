package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductEntityMapper {

    @Mapping(source = "brandId", target = "brandId", qualifiedByName = "mapIdToBrandEntity")
    @Mapping(source = "categoryId", target = "categories", qualifiedByName = "mapIdsToCategoryEntities")
    ProductEntity toProductEntity(Product product);

    @Mapping(source = "brandId", target = "brandId", qualifiedByName = "mapBrandEntityToId")
    @Mapping(source = "categories", target = "categoryId", qualifiedByName = "mapCategoryEntitiesToIds")
    Product toProduct(ProductEntity productEntity);

    @Mapping(source = "brandId", target = "brandId", qualifiedByName = "mapBrandEntityToId")
    @Mapping(source = "categories", target = "categoryId", qualifiedByName = "mapCategoryEntitiesToIds")
    ProductResponseDto toProductResponseDto(ProductEntity productEntity);

    @Mapping(source = "brandId", target = "brandId", qualifiedByName = "mapBrandEntityToId")
    @Mapping(source = "categories", target = "categoryId", qualifiedByName = "mapCategoryEntitiesToIds")
    ProductPaginationResponseDto toProductPaginationResponseDto(ProductEntity productEntity);

    @Mapping(source = "brandId", target = "brandId", qualifiedByName = "mapBrandEntityToId")
    @Mapping(source = "categories", target = "categoryId", qualifiedByName = "mapCategoryEntitiesToIds")
    ProductListResponseDto toProductListResponseDto(ProductEntity productEntity);
}