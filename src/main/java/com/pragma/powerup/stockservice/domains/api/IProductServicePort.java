package com.pragma.powerup.stockservice.domains.api;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductServicePort {
    void createProduct(Product product);
//    void updateProduct(Product product);
//    Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy);
//    List<ProductListResponseDto> getListProduct();
//    ProductResponseDto getProduct(Long idProduct);
//    void deleteProduct(Long idProduct);
}
