package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.util.DomainConstants.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryServicePort {
    void createCategory(Category category);
    PagedList<Category> getPaginationCategoryByOrder(CategoryPagingRequestDto requestDto);
//    void updateCategory(Category category);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategory(Long idCategory);
//    void deleteCategory(Long idCategory);
}
