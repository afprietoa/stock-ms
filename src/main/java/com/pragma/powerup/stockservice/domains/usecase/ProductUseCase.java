package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IProductResponseMapper;
import com.pragma.powerup.stockservice.domains.api.IProductServicePort;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final IProductResponseMapper productResponseMapper;

    public ProductUseCase(IProductPersistencePort productPersistencePort, IProductResponseMapper productResponseMapper) {
        this.productPersistencePort = productPersistencePort;
        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public void createProduct(Product product) {
        productPersistencePort.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        Product tempProduct = productResponseMapper.toProduct(this.getProduct(product.getIdProduct()));
        tempProduct.setName(product.getName());
        tempProduct.setPrice(product.getPrice());
        tempProduct.setDescription(product.getDescription());
            tempProduct.setUrlImage(product.getUrlImage());
        tempProduct.setQuantity(product.getQuantity());
        productPersistencePort.saveProduct(tempProduct);
    }

    @Override
    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
        return productPersistencePort.getPaginationProduct(pageSize, sortBy);
    }

    @Override
    public List<ProductListResponseDto> getListProduct() {
        return productPersistencePort.getListProduct();
    }

    @Override
    public ProductResponseDto getProduct(Long idProduct) {
        return productPersistencePort.getProductById(idProduct);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productPersistencePort.deleteProduct(idProduct);
    }
}
