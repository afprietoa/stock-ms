package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface ICategoryServicePort {
    void createCategory(Category category);
    PagedList<Category> getPaginationCategoryByOrder(CategoryPagingRequestDto requestDto);
    Category findCategoryById(Long categoryId);
//    void updateCategory(Category category);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategory(Long idCategory);
//    void deleteCategory(Long idCategory);
}
