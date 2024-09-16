package com.pragma.powerup.stockservice.domains.spi;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Category getCategoryByName(String categoryName);
    PagedList<Category> getPaginationCategories(CategoryPagingRequestDto requestDto);

    Long getCategoryById(Long categoryId);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategoryById(Long idCategory);
//    void deleteCategory(Long idCategory);
}
