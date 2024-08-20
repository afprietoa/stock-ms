package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandHandler {
    void createBrand(BrandCreateRequestDto brandCreateRequestDto);
    void updateBrand(BrandUpdateRequestDto brandUpdateRequestDto);
    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
    List<BrandListResponseDto> getListBrand();
    BrandResponseDto getBrand(Long idBrand);
    void deleteBrand(Long idBrand);
}
