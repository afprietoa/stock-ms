package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandUpdateRequestDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandRequestMapper {
    Brand toCreateBrand(BrandCreateRequestDto brandCreateRequestDto);
//    Brand toUpdateBrand(BrandUpdateRequestDto brandUpdateRequestDto);
}
