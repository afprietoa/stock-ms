package com.pragma.powerup.stockservice.Product;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductPagingRequestDto;
import com.pragma.powerup.stockservice.domains.exceptions.ProductBrandNotFoundException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryInvalidException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryRepeatedException;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.model.Product;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;
import com.pragma.powerup.stockservice.domains.usecase.ProductUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class ProductUseCaseTest {
    @MockBean
    private IProductPersistencePort productPersistencePort;

    @MockBean
    private ICategoryPersistencePort categoryPersistencePort;

    @MockBean
    private IBrandPersistencePort brandPersistencePort;

    @Autowired
    private ProductUseCase productUseCase;

    @Test
    void createProduct_ValidProduct_Success() {
        Product product = new Product(1L, 1L, BigDecimal.valueOf(10), "Description", "Product Name", BigDecimal.valueOf(100), "urlImage", List.of(1L, 2L));

        // Mocks
        when(categoryPersistencePort.getCategoryById(1L)).thenReturn(1L);
        when(categoryPersistencePort.getCategoryById(2L)).thenReturn(2L);
        when(brandPersistencePort.getBrandById(1L)).thenReturn(1L);

        productUseCase.createProduct(product);

        verify(productPersistencePort, times(1)).saveProduct(product);
    }

    @Test
    void createProduct_InvalidCategories_ThrowsException() {
        Product product = new Product(1L, 1L, BigDecimal.valueOf(10), "Description", "Product Name", BigDecimal.valueOf(100), "urlImage", List.of());

        Exception exception = assertThrows(ProductCategoryInvalidException.class, () -> productUseCase.createProduct(product));
        assertEquals("The product must have between 1 and 3 categories.", exception.getMessage());
    }

    @Test
    void createProduct_DuplicatedCategories_ThrowsException() {
        // Arrange
        List<Long> duplicatedCategoryIds = Arrays.asList(1L, 1L); // Simulate duplicated category IDs
        Product product = new Product(1L, 1L, BigDecimal.valueOf(10), "Description", "Product Name", BigDecimal.valueOf(100), "urlImage", duplicatedCategoryIds);

        // Act & Assert
        assertThrows(ProductCategoryRepeatedException.class, () -> {
            productUseCase.createProduct(product);
        });
    }

    @Test
    void createProduct_BrandNotFound_ThrowsException() {
        Product product = new Product(1L, null, BigDecimal.valueOf(10), "Description", "Product Name", BigDecimal.valueOf(100), "urlImage", List.of(1L, 2L));

        Exception exception = assertThrows(ProductBrandNotFoundException.class, () -> productUseCase.createProduct(product));
        assertEquals("Marca no encontrada", exception.getMessage());
    }

    @Test
    public void getPaginationProducts_ShouldReturnPagedProducts() {
        // Arrange
        ProductPagingRequestDto requestDto = new ProductPagingRequestDto();
        requestDto.setPageNumber(1);
        requestDto.setPageSize(10);

        PagedList<Product> pagedProducts = new PagedList<>(List.of(new Product()), 1, 10, 1);
        when(productPersistencePort.getPaginationProducts(requestDto)).thenReturn(pagedProducts);

        // Act
        PagedList<Product> result = productUseCase.getPaginationProductByOrder(requestDto);

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }
}
