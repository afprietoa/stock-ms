package com.pragma.powerup.stockservice.domains.spi;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Brand getBrandByName(String brandName);
    PagedList<Brand> getPaginationBrands(BrandPagingRequestDto requestDto);

    Long getBrandById(Long brandId);
//    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
//    List<BrandListResponseDto> getListBrand();
//    BrandResponseDto getBrandById(Long idBrand);
//    void deleteBrand(Long idBrand);
}
