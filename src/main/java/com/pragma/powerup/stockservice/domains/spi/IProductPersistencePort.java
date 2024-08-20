package com.pragma.powerup.stockservice.domains.spi;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy);
    List<ProductListResponseDto> getListProduct();
    ProductResponseDto getProductById(Long idProduct);
    void deleteProduct(Long idProduct);
}
