package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.CategoryNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryMySqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryMapper;


    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryMapper.toCategoryEntity(category));
    }

    @Override
    public Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(0,pageSize, Sort.by(sortBy).ascending());
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);
        return categoryEntityPage.map(categoryMapper::toCategoryPaginationResponseDto);
    }

    @Override
    public List<CategoryListResponseDto> getListCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) throw new NoDataFoundException();
        return categoryEntityList.stream().map(categoryMapper::toCategoryListResponseDto).toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long idCategory) {
        CategoryEntity categoryEntity = categoryRepository.findById(idCategory).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toCategoryResponseDto(categoryEntity);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
