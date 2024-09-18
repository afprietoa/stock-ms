package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface ICategoryHandler {
    void createCategory(CategoryCreateRequestDto categoryCreateRequestDto);
    PagedList<CategoryResponseDto> getPagedCategories(CategoryPagingRequestDto requestDto);
    Category getCategory(Long idCategory);
//    void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategory(Long idCategory);
//    void deleteCategory(Long idCategory);
}
