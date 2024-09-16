package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductMySqlAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productMapper;


    @Override
    public void saveProduct(Product product) {
        System.out.println("ProductEntity to be saved: " + productMapper.toProductEntity(product));
        productRepository.save(productMapper.toProductEntity(product));
    }

    @Override
    public PagedList<Product> getPaginationProducts(ProductPagingRequestDto requestDto) {
        Pageable pageable = PageRequest.of(
                requestDto.getPageNumber() - 1,
                requestDto.getPageSize(),
                requestDto.isAscending() ? Sort.by(requestDto.getOrderBy()).ascending() : Sort.by(requestDto.getOrderBy()).descending()
        );

        Page<ProductEntity> pagedResult = productRepository.findAll(pageable);

        List<Product> products = pagedResult.getContent().stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());

        return PagedList.of(products, requestDto.getPageNumber(), requestDto.getPageSize(), pagedResult.getTotalElements());
    }

//    @Override
//    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
//        Pageable pageable = PageRequest.of(0,pageSize, Sort.by(sortBy).ascending());
//        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
//        return productEntityPage.map(productMapper::toProductPaginationResponseDto);
//    }
//
//    @Override
//    public List<ProductListResponseDto> getListProduct() {
//        List<ProductEntity> productEntityList = productRepository.findAll();
//        if (productEntityList.isEmpty()) throw new NoDataFoundException();
//        return productEntityList.stream().map(productMapper::toProductListResponseDto).toList();
//    }
//
//    @Override
//    public ProductResponseDto getProductById(Long idProduct) {
//        ProductEntity productEntity = productRepository.findById(idProduct).orElseThrow(ProductNotFoundException::new);
//        return productMapper.toProductResponseDto(productEntity);
//    }
//
//    @Override
//    public void deleteProduct(Long idProduct) {
//        productRepository.deleteById(idProduct);
//    }
}
