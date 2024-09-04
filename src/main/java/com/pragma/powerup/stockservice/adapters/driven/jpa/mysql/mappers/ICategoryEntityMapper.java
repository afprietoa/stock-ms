package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    @Named("mapIdToCategoryEntity")
    default CategoryEntity mapIdToCategoryEntity(Long id) {
        if (id == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setIdCategory(id);
        return categoryEntity;
    }

    @Named("mapCategoryEntityToId")
    default Long mapCategoryEntityToId(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return categoryEntity.getIdCategory();
    }

    @Named("mapIdsToCategoryEntities")
    default Set<CategoryEntity> mapIdsToCategoryEntities(Set<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(this::mapIdToCategoryEntity)
                .collect(Collectors.toSet());
    }

    @Named("mapCategoryEntitiesToIds")
    default Set<Long> mapCategoryEntitiesToIds(Set<CategoryEntity> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(this::mapCategoryEntityToId)
                .collect(Collectors.toSet());
    }
}
