package com.pragma.powerup.stockservice.adapters.driving.http.handlers.Impl;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.ICategoryHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.powerup.stockservice.domains.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryHandlerImpl implements ICategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    @Override
    public void createCategory(CategoryCreateRequestDto CategoryCreateRequestDto) {
        categoryServicePort.createCategory(categoryRequestMapper.toCreateCategory(CategoryCreateRequestDto));
    }

//    @Override
//    public void updateCategory(CategoryUpdateRequestDto CategoryUpdateRequestDto) {
//        categoryServicePort.updateCategory(categoryRequestMapper.toUpdateCategory(CategoryUpdateRequestDto));
//    }
//
//    @Override
//    public Page<CategoryPaginationResponseDto> getPaginationCategory(Integer pageSize, String sortBy) {
//        return categoryServicePort.getPaginationCategory(pageSize, sortBy);
//    }
//
//    @Override
//    public List<CategoryListResponseDto> getListCategory() {
//        return categoryServicePort.getListCategory();
//    }
//
//    @Override
//    public CategoryResponseDto getCategory(Long idCategory) {
//        return categoryServicePort.getCategory(idCategory);
//    }
//
//    @Override
//    public void deleteCategory(Long idCategory) {
//        categoryServicePort.deleteCategory(idCategory);
//    }
}
