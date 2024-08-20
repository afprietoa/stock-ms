package com.pragma.powerup.stockservice.adapters.driving.http.handlers.Impl;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IBrandHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma.powerup.stockservice.domains.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandHandlerImpl implements IBrandHandler {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;

    @Override
    public void createBrand(BrandCreateRequestDto BrandCreateRequestDto) {
        brandServicePort.createBrand(brandRequestMapper.toCreateBrand(BrandCreateRequestDto));
    }

    @Override
    public void updateBrand(BrandUpdateRequestDto BrandUpdateRequestDto) {
        brandServicePort.updateBrand(brandRequestMapper.toUpdateBrand(BrandUpdateRequestDto));
    }

    @Override
    public Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy) {
        return brandServicePort.getPaginationBrand(pageSize, sortBy);
    }

    @Override
    public List<BrandListResponseDto> getListBrand() {
        return brandServicePort.getListBrand();
    }

    @Override
    public BrandResponseDto getBrand(Long idBrand) {
        return brandServicePort.getBrand(idBrand);
    }

    @Override
    public void deleteBrand(Long idBrand) {
        brandServicePort.deleteBrand(idBrand);
    }

}
