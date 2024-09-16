package com.pragma.powerup.stockservice.adapters.driving.http.handlers.Impl;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IBrandHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma.powerup.stockservice.domains.api.IBrandServicePort;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandHandlerImpl implements IBrandHandler {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Override
    public void createBrand(BrandCreateRequestDto BrandCreateRequestDto) {
        brandServicePort.createBrand(brandRequestMapper.toCreateBrand(BrandCreateRequestDto));
    }

    @Override
    public PagedList<BrandResponseDto> getPagedBrands(BrandPagingRequestDto requestDto) {
        PagedList<Brand> pagedBrands = brandServicePort.getPaginationBrandByOrder(requestDto);
        List<BrandResponseDto> responseDtos = pagedBrands.getContent().stream()
                .map(brandResponseMapper::toBrandResponseDto)
                .collect(Collectors.toList());

        return new PagedList<>(
                responseDtos,
                pagedBrands.getPageNumber(),
                pagedBrands.getPageSize(),
                pagedBrands.getTotalElements()
//                pagedBrands.isFirst(),
//                pagedBrands.isLast()
        );
    }

//    @Override
//    public void updateBrand(BrandUpdateRequestDto BrandUpdateRequestDto) {
//        brandServicePort.updateBrand(brandRequestMapper.toUpdateBrand(BrandUpdateRequestDto));
//    }
//
//    @Override
//    public Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy) {
//        return brandServicePort.getPaginationBrand(pageSize, sortBy);
//    }
//
//    @Override
//    public List<BrandListResponseDto> getListBrand() {
//        return brandServicePort.getListBrand();
//    }
//
//    @Override
//    public BrandResponseDto getBrand(Long idBrand) {
//        return brandServicePort.getBrand(idBrand);
//    }
//
//    @Override
//    public void deleteBrand(Long idBrand) {
//        brandServicePort.deleteBrand(idBrand);
//    }

}
