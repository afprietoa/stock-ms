package com.pragma.powerup.stockservice.adapters.driving.http.handlers.Impl;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IProductHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IProductRequestMapper;
import com.pragma.powerup.stockservice.domains.api.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductHandlerImpl implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;

    @Override
    public void createProduct(ProductCreateRequestDto productCreateRequestDto) {
        productServicePort.createProduct(productRequestMapper.toCreateProduct(productCreateRequestDto));
    }

    @Override
    public void updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
        productServicePort.updateProduct(productRequestMapper.toUpdateProduct(productUpdateRequestDto));
    }

    @Override
    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
        return productServicePort.getPaginationProduct(pageSize, sortBy);
    }

    @Override
    public List<ProductListResponseDto> getListProduct() {
        return productServicePort.getListProduct();
    }

    @Override
    public ProductResponseDto getProduct(Long idProduct) {
        return productServicePort.getProduct(idProduct);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productServicePort.deleteProduct(idProduct);
    }
}
