package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class})
public interface IProductEntityMapper {


    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrandIdToBrandEntity")
    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "mapCategoryIdsToCategoryEntities")
    ProductEntity toProductEntity(Product product);

    @Mapping(target = "brandId", source = "brand.idBrand")
    @Mapping(target = "categoryIds", source = "categories", qualifiedByName = "mapCategoryEntitiesToCategoryIds")
    Product toProduct(ProductEntity productEntity);

    // Modify to map List<Long> to Set<CategoryEntity>
    @Named("mapCategoryIdsToCategoryEntities")
    default Set<CategoryEntity> mapCategoryIdsToCategoryEntities(List<Long> categoryIds) {
        if (categoryIds == null) {
            return new HashSet<>();
        }
        return categoryIds.stream()
                .map(id -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setIdCategory(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }

    // Modify to map Set<CategoryEntity> to List<Long>
    @Named("mapCategoryEntitiesToCategoryIds")
    default List<Long> mapCategoryEntitiesToCategoryIds(Set<CategoryEntity> categoryEntities) {
        if (categoryEntities == null) {
            return List.of();
        }
        return categoryEntities.stream()
                .map(CategoryEntity::getIdCategory)
                .collect(Collectors.toList());
    }

    @Named("mapBrandIdToBrandEntity")
    default BrandEntity mapBrandIdToBrandEntity(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandEntity brand = new BrandEntity();
        brand.setIdBrand(brandId);
        return brand;
    }

//    Product toProduct(ProductEntity productEntity);
//
//    ProductResponseDto toProductResponseDto(ProductEntity productEntity);
//
//    ProductPaginationResponseDto toProductPaginationResponseDto(ProductEntity productEntity);
//
//    ProductListResponseDto toProductListResponseDto(ProductEntity productEntity);
}