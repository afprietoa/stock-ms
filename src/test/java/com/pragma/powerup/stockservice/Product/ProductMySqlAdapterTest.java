package com.pragma.powerup.stockservice.Product;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.ProductMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IBrandRepository;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class ProductMySqlAdapterTest {
    @MockBean
    private IProductRepository productRepository;

    @MockBean
    private IProductEntityMapper productEntityMapper;

    @Autowired
    private ProductMySqlAdapter productMySqlAdapter;

    @Test
    void saveProduct_ValidProduct_Success() {
        Product product = new Product(1L, 1L, BigDecimal.valueOf(10), "Description", "Product Name", BigDecimal.valueOf(100), "urlImage", List.of(1L, 2L));
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());

        when(productEntityMapper.toProductEntity(product)).thenReturn(productEntity);

        productMySqlAdapter.saveProduct(product);

        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    public void getPaginationProducts_ShouldReturnPagedResult_WithSorting() {
        // Arrange
        // Arrange
        ProductPagingRequestDto requestDto = new ProductPagingRequestDto();
        requestDto.setPageNumber(1);
        requestDto.setPageSize(10);
        requestDto.setOrderBy("name");  // Añadir propiedad de ordenación válida
        requestDto.setAscending(true);  // Orden ascendente

        // Mocking datos
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        List<ProductEntity> productEntities = Collections.singletonList(new ProductEntity());
        Page<ProductEntity> pagedResult = new PageImpl<>(productEntities, pageable, 1);

        when(productRepository.findAll(any(Pageable.class))).thenReturn(pagedResult);
        when(productEntityMapper.toProduct(any(ProductEntity.class))).thenReturn(new Product());

        // Act
        PagedList<Product> result = productMySqlAdapter.getPaginationProducts(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getContent().size());
    }
}
