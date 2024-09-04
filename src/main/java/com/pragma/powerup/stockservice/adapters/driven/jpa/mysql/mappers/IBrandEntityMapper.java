package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {

    @Mapping(source = "id", target = "idBrand")
    BrandEntity toBrandEntity(Brand brand);

    @Mapping(source = "idBrand", target = "id")
    Brand toBrand(BrandEntity brandEntity);

    @Named("mapIdToBrandEntity")
    default BrandEntity mapIdToBrandEntity(Long id) {
        if (id == null) {
            return null;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setIdBrand(id);
        return brandEntity;
    }

    @Named("mapBrandEntityToId")
    default Long mapBrandEntityToId(BrandEntity brandEntity) {
        if (brandEntity == null) {
            return null;
        }
        return brandEntity.getIdBrand();
    }
}