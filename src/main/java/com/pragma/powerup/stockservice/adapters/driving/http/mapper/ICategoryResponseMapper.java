package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {
    Category toCategory(CategoryResponseDto categoryResponseDto);
    CategoryResponseDto toCategoryResponseDto(Category category);
    //CategoryPaginationResponseDto<CategoryResponseDto> toCategoryPaginationResponseDto(PagedList<Category> categoryPagedList);
    PagedList<Category> toPagedList (CategoryPaginationResponseDto categoryPaginationResponseDto);
}
