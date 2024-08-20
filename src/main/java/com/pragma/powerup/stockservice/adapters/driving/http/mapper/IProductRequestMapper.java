package com.pragma.powerup.stockservice.adapters.driving.http.mapper;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductUpdateRequestDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {
    Product toCreateProduct(ProductCreateRequestDto productCreateRequestDto);
    Product toUpdateProduct(ProductUpdateRequestDto productUpdateRequestDto);
}
