package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.ProductNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ProductMySqlAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;


    @Override
    public void saveProduct(Product product) {
        System.out.println("ProductEntity to be saved: " + productEntityMapper.toProductEntity(product));
        productRepository.save(productEntityMapper.toProductEntity(product));
    }

//    @Override
//    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
//        Pageable pageable = PageRequest.of(0,pageSize, Sort.by(sortBy).ascending());
//        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
//        return productEntityPage.map(productEntityMapper::toProductPaginationResponseDto);
//    }
//
//    @Override
//    public List<ProductListResponseDto> getListProduct() {
//        List<ProductEntity> productEntityList = productRepository.findAll();
//        if (productEntityList.isEmpty()) throw new NoDataFoundException();
//        return productEntityList.stream().map(productEntityMapper::toProductListResponseDto).toList();
//    }
//
//    @Override
//    public ProductResponseDto getProductById(Long idProduct) {
//        ProductEntity productEntity = productRepository.findById(idProduct).orElseThrow(ProductNotFoundException::new);
//        return productEntityMapper.toProductResponseDto(productEntity);
//    }
//
//    @Override
//    public void deleteProduct(Long idProduct) {
//        productRepository.deleteById(idProduct);
//    }
}
