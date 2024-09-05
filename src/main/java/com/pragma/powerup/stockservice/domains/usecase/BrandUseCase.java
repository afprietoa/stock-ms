package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma.powerup.stockservice.domains.api.IBrandServicePort;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;
    private final IBrandResponseMapper brandResponseMapper;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort, IBrandResponseMapper brandResponseMapper) {
        this.brandPersistencePort = brandPersistencePort;
        this.brandResponseMapper = brandResponseMapper;
    }

    @Override
    public void createBrand(Brand Brand) {
        brandPersistencePort.saveBrand(Brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        Brand tempBrand = brandResponseMapper.toBrand(this.getBrand(brand.getIdBrand()));
        tempBrand.setName(brand.getName());
        tempBrand.setDescription(brand.getDescription());
        brandPersistencePort.saveBrand(tempBrand);
    }

    @Override
    public Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy) {
        return brandPersistencePort.getPaginationBrand(pageSize, sortBy);
    }

    @Override
    public List<BrandListResponseDto> getListBrand() {
        return brandPersistencePort.getListBrand();
    }

    @Override
    public BrandResponseDto getBrand(Long idBrand) {
        return brandPersistencePort.getBrandById(idBrand);
    }

    @Override
    public void deleteBrand(Long idBrand) {
        brandPersistencePort.deleteBrand(idBrand);
    }
}
