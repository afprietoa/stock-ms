package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.powerup.stockservice.domains.api.ICategoryServicePort;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryResponseMapper categoryResponseMapper;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, ICategoryResponseMapper categoryResponseMapper) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @Override
    public void createCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        Category tempCategory = categoryResponseMapper.toCategory(this.getCategory(category.getIdCategory()));
        tempCategory.setName(category.getName());
        tempCategory.setDescription(category.getDescription());
        categoryPersistencePort.saveCategory(tempCategory);
    }

    @Override
    public Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy) {
        return categoryPersistencePort.getPaginationCategory(pageSize, sortBy);
    }

    @Override
    public List<CategoryListResponseDto> getListCategory() {
        return categoryPersistencePort.getListCategory();
    }

    @Override
    public CategoryResponseDto getCategory(Long idCategory) {
        return categoryPersistencePort.getCategoryById(idCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryPersistencePort.deleteCategory(idCategory);
    }
}
