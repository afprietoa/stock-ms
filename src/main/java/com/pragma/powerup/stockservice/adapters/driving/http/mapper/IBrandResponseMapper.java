package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    Brand toBrand(BrandResponseDto brandResponseDto);
    BrandResponseDto toBrandResponseDto(Brand brand);
    //BrandPaginationResponseDto<BrandResponseDto> toBrandPaginationResponseDto(PagedList<Brand> brandPagedList);
    PagedList<Brand> toPagedList (BrandPaginationResponseDto brandPaginationResponseDto);
}
