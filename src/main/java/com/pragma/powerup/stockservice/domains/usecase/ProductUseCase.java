package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.ProductNotFoundException;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.domains.api.IProductServicePort;
import com.pragma.powerup.stockservice.domains.exceptions.ProductBrandNotFoundException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryInvalidException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryRepeatedException;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
//    private final IProductResponseMapper productResponseMapper;


    public ProductUseCase(IProductPersistencePort productPersistencePort
              , ICategoryPersistencePort categoryPersistencePort
              , IBrandPersistencePort brandPersistencePort
//            , IProductResponseMapper productResponseMapper
    ) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
//        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public void createProduct(Product product) {
        product.setCategoryIds(validateCategories(product.getCategoryIds()));
        product.setBrandId(validateBrand(product.getBrandId()));

        // Log the ProductEntity for debugging
        System.out.println("Product to be saved: " + product);
        productPersistencePort.saveProduct(product);
    }

    private List<Long> validateCategories(List<Long> categoryIds) {
        if (categoryIds.size() < 1 || categoryIds.size() > 3) {
            throw new ProductCategoryInvalidException("The product must have between 1 and 3 categories.");
        }

        // Manual check for duplicates in List
        if (categoryIds.stream().distinct().count() != categoryIds.size()) {
            throw new ProductCategoryRepeatedException("The product contains duplicated categories.");
        }

        return categoryIds.stream()
                .map(this::getCategoryById)
                .collect(Collectors.toList());
    }

    private Long validateBrand(Long brandId) {

        if (brandId == null) {
            throw new ProductBrandNotFoundException("Marca no encontrada");
        }

        return this.getBrandById(brandId);
    }

    private Long getCategoryById(Long categoryId) {
        return categoryPersistencePort.getCategoryById(categoryId);
    }

    private Long getBrandById(Long brandId) {
        return brandPersistencePort.getBrandById(brandId);
    }

    @Override
    public PagedList<Product> getPaginationProductByOrder(ProductPagingRequestDto requestDto) {
        // Lógica de paginación, mapeo y validaciones necesarias
        return productPersistencePort.getPaginationProducts(requestDto);
    }
//
//    @Override
//    public void updateProduct(Product product) {
//        Product tempProduct = productResponseMapper.toProduct(this.getProduct(product.getIdProduct()));
//        tempProduct.setName(product.getName());
//        tempProduct.setPrice(product.getPrice());
//        tempProduct.setDescription(product.getDescription());
//            tempProduct.setUrlImage(product.getUrlImage());
//        tempProduct.setQuantity(product.getQuantity());
//        productPersistencePort.saveProduct(tempProduct);
//    }
//
//    @Override
//    public Page<ProductPaginationResponseDto> getPaginationProduct(Integer pageSize, String sortBy) {
//        return productPersistencePort.getPaginationProduct(pageSize, sortBy);
//    }
//
//    @Override
//    public List<ProductListResponseDto> getListProduct() {
//        return productPersistencePort.getListProduct();
//    }
//
//    @Override
//    public ProductResponseDto getProduct(Long idProduct) {
//        return productPersistencePort.getProductById(idProduct);
//    }
//
//    @Override
//    public void deleteProduct(Long idProduct) {
//        productPersistencePort.deleteProduct(idProduct);
//    }
}
