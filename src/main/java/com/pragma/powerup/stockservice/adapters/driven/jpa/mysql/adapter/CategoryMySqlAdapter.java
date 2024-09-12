package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.CategoryNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryMySqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryMapper;


    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryMapper.toCategoryEntity(category));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .map(categoryMapper::toCategory)
                .orElse(null);
    }

    @Override
    public PagedList<Category> getPaginationCategories(CategoryPagingRequestDto requestDto) {
        Pageable pageable = PageRequest.of(
                requestDto.getPageNumber() - 1,
                requestDto.getPageSize(),
                requestDto.isAscending() ? Sort.by(requestDto.getOrderBy()).ascending() : Sort.by(requestDto.getOrderBy()).descending()
        );

        Page<CategoryEntity> pagedResult = categoryRepository.findAll(pageable);

        List<Category> categories = pagedResult.getContent().stream()
                .map(categoryMapper::toCategory)
                .collect(Collectors.toList());

        return PagedList.of(categories, requestDto.getPageNumber(), requestDto.getPageSize(), pagedResult.getTotalElements());
    }

//    @Override
//    public Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy) {
//        Pageable pageable = PageRequest.of(0,pageSize, Sort.by(sortBy).ascending());
//        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);
//        return categoryEntityPage.map(categoryMapper::toCategoryPaginationResponseDto);
//    }
//
//    @Override
//    public List<CategoryListResponseDto> getListCategory() {
//        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
//        if (categoryEntityList.isEmpty()) throw new NoDataFoundException();
//        return categoryEntityList.stream().map(categoryMapper::toCategoryListResponseDto).toList();
//    }
//
//    @Override
//    public CategoryResponseDto getCategoryById(Long idCategory) {
//        CategoryEntity categoryEntity = categoryRepository.findById(idCategory).orElseThrow(CategoryNotFoundException::new);
//        return categoryMapper.toCategoryResponseDto(categoryEntity);
//    }
//
//    @Override
//    public void deleteCategory(Long idCategory) {
//        categoryRepository.deleteById(idCategory);
//    }

}
