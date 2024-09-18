package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface IBrandServicePort {
    void createBrand(Brand brand);
    PagedList<Brand> getPaginationBrandByOrder(BrandPagingRequestDto requestDto);
    Brand findBrandById(Long brandId);
//    void updateBrand(Brand brand);
//    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
//    List<BrandListResponseDto> getListBrand();
//    BrandResponseDto getBrand(Long idBrand);
//    void deleteBrand(Long idBrand);
}
