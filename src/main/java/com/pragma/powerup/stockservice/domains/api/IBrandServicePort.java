package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandServicePort {
    void createBrand(Brand brand);
    void updateBrand(Brand brand);
    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
    List<BrandListResponseDto> getListBrand();
    BrandResponseDto getBrand(Long idBrand);
    void deleteBrand(Long idBrand);
}
