package com.pragma.powerup.stockservice.adapters.driving.http.handlers;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductHandler {
    void createProduct(ProductCreateRequestDto productCreateRequestDto);
    PagedList<ProductResponseDto> getPagedProducts(ProductPagingRequestDto requestDto);
//    void updateProduct(ProductUpdateRequestDto productUpdateRequestDto);
//    Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy);
//    List<ProductListResponseDto> getListProduct();
//    ProductResponseDto getProduct(Long idProduct);
//    void deleteProduct(Long idProduct);
}
