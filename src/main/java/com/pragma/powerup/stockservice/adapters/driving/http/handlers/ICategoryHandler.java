package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryHandler {
    void createCategory(CategoryCreateRequestDto categoryCreateRequestDto);
    void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
    List<CategoryListResponseDto> getListCategory();
    CategoryResponseDto getCategory(Long idCategory);
    void deleteCategory(Long idCategory);
}
