package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryHandler {
    void createCategory(CategoryCreateRequestDto categoryCreateRequestDto);
    PagedList<CategoryResponseDto> getCategoriesPaged(CategoryPagingRequestDto requestDto);
//    void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategory(Long idCategory);
//    void deleteCategory(Long idCategory);
}
