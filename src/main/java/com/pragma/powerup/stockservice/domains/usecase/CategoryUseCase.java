package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.powerup.stockservice.domains.api.ICategoryServicePort;
import com.pragma.powerup.stockservice.domains.exceptions.*;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
//    private final ICategoryResponseMapper categoryResponseMapper;

    public CategoryUseCase(
            ICategoryPersistencePort categoryPersistencePort
//            ,ICategoryResponseMapper categoryResponseMapper
    ) {
        this.categoryPersistencePort = categoryPersistencePort;
//        this.categoryResponseMapper = categoryResponseMapper;
    }

    @Override
    public void createCategory(Category category) {
        validateCategory(category);
        categoryPersistencePort.saveCategory(category);
    }

    private void validateCategory(Category category) {
        validateField(category.getName(), "Nombre de la categoría", 50);
        validateField(category.getDescription(), "Descripción de la categoría", 90);

        if (categoryPersistencePort.getCategoryByName(category.getName()) != null) {
            throw new CategoryNameAlreadyExistsException("El nombre de la categoría ya existe.");
        }
    }

    private void validateField(String field, String fieldName, int maxLength) {
        if (field == null || field.isEmpty()) {
            throw new CategoryFieldIsRequiredException(fieldName + " es requerido.");
        }
        if (field.length() > maxLength) {
            throw new CategoryFieldIsTooLongException(fieldName + " excede el límite de " + maxLength + " caracteres.");
        }
    }

    @Override
    public PagedList<Category> getPaginationCategoryByOrder(CategoryPagingRequestDto requestDto) {
        return categoryPersistencePort.getPaginationCategories(requestDto);
    }


//    @Override
//    public void updateCategory(Category category) {
//        Category tempCategory = categoryResponseMapper.toCategory(this.getCategory(category.getIdCategory()));
//        tempCategory.setName(category.getName());
//        tempCategory.setDescription(category.getDescription());
//        categoryPersistencePort.saveCategory(tempCategory);
//    }
//
//    @Override
//    public Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy) {
//        return categoryPersistencePort.getPaginationCategory(pageSize, sortBy);
//    }
//
//    @Override
//    public List<CategoryListResponseDto> getListCategory() {
//        return categoryPersistencePort.getListCategory();
//    }
//
//    @Override
//    public CategoryResponseDto getCategory(Long idCategory) {
//        return categoryPersistencePort.getCategoryById(idCategory);
//    }
//
//    @Override
//    public void deleteCategory(Long idCategory) {
//        categoryPersistencePort.deleteCategory(idCategory);
//    }

}
