package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface IBrandHandler {
    void createBrand(BrandCreateRequestDto brandCreateRequestDto);
    PagedList<BrandResponseDto> getPagedBrands(BrandPagingRequestDto requestDto);
//    void updateBrand(BrandUpdateRequestDto brandUpdateRequestDto);
//    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
//    List<BrandListResponseDto> getListBrand();
//    BrandResponseDto getBrand(Long idBrand);
//    void deleteBrand(Long idBrand);
}
