package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class})
public interface IProductEntityMapper {

    @Mapping(target = "brand", source = "brandId")
    @Mapping(target = "categories", source = "categoryIds")
    ProductEntity toProductEntity(Product product);

    @Mapping(target = "brandId", source = "brand.idBrand")
    @Mapping(target = "categoryIds", source = "categories")
    Product toProduct(ProductEntity productEntity);

    default BrandEntity map(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setIdBrand(brandId);
        return brandEntity;
    }

    default Set<CategoryEntity> map(List<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (Long categoryId : categoryIds) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setIdCategory(categoryId);
            categoryEntities.add(categoryEntity);
        }

        return categoryEntities;
    }

    default List<Long> map(Set<CategoryEntity> categories) {
        if (categories == null) {
            return null;
        }
        List<Long> categoryIds = new ArrayList<>();
        for (CategoryEntity category : categories) {
            categoryIds.add(category.getIdCategory());
        }

        return categoryIds;
    }

//    Product toProduct(ProductEntity productEntity);
//
//    ProductResponseDto toProductResponseDto(ProductEntity productEntity);
//
//    ProductPaginationResponseDto toProductPaginationResponseDto(ProductEntity productEntity);
//
//    ProductListResponseDto toProductListResponseDto(ProductEntity productEntity);
}