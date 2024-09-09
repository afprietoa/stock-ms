package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    Category toCreateCategory(CategoryCreateRequestDto categoryCreateRequestDto);
//    Category toUpdateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
}
