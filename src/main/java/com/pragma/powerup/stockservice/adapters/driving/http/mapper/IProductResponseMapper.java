package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.BrandNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.CategoryNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IBrandEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryResponseMapper.class, IBrandResponseMapper.class})
public interface IProductResponseMapper {


    // Map from ProductResponseDto to Product
    @Mapping(target = "brandId", source = "brand.idBrand")
    @Mapping(target = "categoryIds", source = "categories")
    Product toProduct(ProductResponseDto productResponseDto);

    // Map from Product to ProductResponseDto using @Context for ports
    @Mapping(target = "brand", expression = "java(mapBrandIdToBrandResponse(product.getBrandId(), brandPersistencePort))")
    @Mapping(target = "categories", expression = "java(mapIdsToCategoryDto(product.getCategoryIds(), categoryPersistencePort))")
    ProductResponseDto toProductResponseDto(Product product, @Context IBrandPersistencePort brandPersistencePort, @Context ICategoryPersistencePort categoryPersistencePort);

    // Custom method to map brandId (Long) to BrandResponseDto using IBrandPersistencePort
    default BrandResponseDto mapBrandIdToBrandResponse(Long brandId, @Context IBrandPersistencePort brandPersistencePort) {
        if (brandId == null) {
            return null;
        }
        Brand brand = brandPersistencePort.findBrandById(brandId);
        if (brand == null) {
            throw new BrandNotFoundException("Brand not found for id: " + brandId);
        }
        return new BrandResponseDto(brand.getIdBrand(), brand.getName(), brand.getDescription());
    }

    // Custom method to map a list of category IDs to CategoryResponseDto using ICategoryPersistencePort
    default List<CategoryResponseDto> mapIdsToCategoryDto(List<Long> categoryIds, @Context ICategoryPersistencePort categoryPersistencePort) {
        if (categoryIds == null) {
            return null;
        }
        return categoryIds.stream()
                .map(categoryId -> {
                    Category category = categoryPersistencePort.findCategoryById(categoryId);
                    if (category == null) {
                        throw new CategoryNotFoundException("Category not found for id: " + categoryId);
                    }
                    return new CategoryResponseDto(category.getIdCategory(), category.getName(), category.getDescription());
                })
                .collect(Collectors.toList());
    }

    // Method to map from List<CategoryResponseDto> to List<Long> (categoryIds)
    default List<Long> mapCategoryDtoToIds(List<CategoryResponseDto> categoryDtos) {
        if (categoryDtos == null) {
            return null;
        }
        return categoryDtos.stream()
                .map(CategoryResponseDto::idCategory)
                .collect(Collectors.toList());
    }
}