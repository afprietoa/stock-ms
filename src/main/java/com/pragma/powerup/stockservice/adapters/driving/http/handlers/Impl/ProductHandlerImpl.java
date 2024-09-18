package com.pragma.powerup.stockservice.adapters.driving.http.handlers.Impl;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IProductHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IProductResponseMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IProductRequestMapper;
import com.pragma.powerup.stockservice.domains.api.IProductServicePort;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductHandlerImpl implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productResponseMapper;
    private final IBrandPersistencePort brandPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public void createProduct(ProductCreateRequestDto productCreateRequestDto) {
        productServicePort.createProduct(productRequestMapper.toCreateProduct(productCreateRequestDto));
    }

    @Override
    public PagedList<ProductResponseDto> getPagedProducts(ProductPagingRequestDto requestDto) {
        PagedList<Product> pagedProducts = productServicePort.getPaginationProductByOrder(requestDto);

        List<ProductResponseDto> responseDtos = pagedProducts.getContent().stream()
                .map(product -> productResponseMapper.toProductResponseDto(product, brandPersistencePort, categoryPersistencePort))
                .collect(Collectors.toList());

        return new PagedList<>(
                responseDtos,
                pagedProducts.getPageNumber(),
                pagedProducts.getPageSize(),
                pagedProducts.getTotalElements()
        );
    }

//    @Override
//    public void updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
//        productServicePort.updateProduct(productRequestMapper.toUpdateProduct(productUpdateRequestDto));
//    }
//
//    @Override
//    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
//        return productServicePort.getPaginationProduct(pageSize, sortBy);
//    }
//
//    @Override
//    public List<ProductListResponseDto> getListProduct() {
//        return productServicePort.getListProduct();
//    }
//
//    @Override
//    public ProductResponseDto getProduct(Long idProduct) {
//        return productServicePort.getProduct(idProduct);
//    }
//
//    @Override
//    public void deleteProduct(Long idProduct) {
//        productServicePort.deleteProduct(idProduct);
//    }
}
