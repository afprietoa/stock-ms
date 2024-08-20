package com.pragma.powerup.stockservice.domains.spi;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy);
    List<BrandListResponseDto> getListBrand();
    BrandResponseDto getBrandById(Long idBrand);
    void deleteBrand(Long idBrand);
}
