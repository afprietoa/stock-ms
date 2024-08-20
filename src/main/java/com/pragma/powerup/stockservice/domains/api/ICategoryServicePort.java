package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryServicePort {
    void createCategory(Category category);
    void updateCategory(Category category);
    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
    List<CategoryListResponseDto> getListCategory();
    CategoryResponseDto getCategory(Long idCategory);
    void deleteCategory(Long idCategory);
}
