package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
    BrandResponseDto toBrandResponseDto(BrandEntity brandEntity);
    BrandPaginationResponseDto toBrandPaginationResponseDto(BrandEntity brandEntity);
    BrandListResponseDto toBrandListResponseDto(BrandEntity brandEntity);
}
