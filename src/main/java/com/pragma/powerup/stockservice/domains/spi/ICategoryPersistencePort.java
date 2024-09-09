package com.pragma.powerup.stockservice.domains.spi;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Category getCategoryByName(String categoryName);
//    Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy);
//    List<CategoryListResponseDto> getListCategory();
//    CategoryResponseDto getCategoryById(Long idCategory);
//    void deleteCategory(Long idCategory);
}
