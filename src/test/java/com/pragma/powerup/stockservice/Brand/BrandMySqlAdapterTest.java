package com.pragma.powerup.stockservice.Brand;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.BrandMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IBrandEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IBrandRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class BrandMySqlAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    private BrandMySqlAdapter brandMySqlAdapter;

    private Brand brand;
    private BrandEntity brandEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brandMySqlAdapter = new BrandMySqlAdapter(brandRepository, brandEntityMapper);

        // Setup reusable objects
        brand = new Brand();
        brand.setName("Electronics");
        brand.setDescription("Devices");

        brandEntity = new BrandEntity();
    }

    @Test
    public void saveBrand_ShouldSaveBrand() {
        // Arrange
        when(brandEntityMapper.toBrandEntity(brand)).thenReturn(brandEntity);

        // Act
        brandMySqlAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository, times(1)).save(brandEntity);
    }

    @Test
    public void findByName_WhenBrandExists_ShouldReturnBrand() {
        // Arrange
        String brandName = "Electronics";
        when(brandRepository.findByName(brandName)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        // Act
        Brand result = brandMySqlAdapter.getBrandByName(brandName);

        // Assert
        assertEquals(brand, result);
    }

    @Test
    public void findByName_WhenBrandDoesNotExist_ShouldReturnNull() {
        // Arrange
        String brandName = "NonExistingBrand";
        when(brandRepository.findByName(brandName)).thenReturn(Optional.empty());

        // Act
        Brand result = brandMySqlAdapter.getBrandByName(brandName);

        // Assert
        assertNull(result);
    }

    @Test
    public void saveBrand_ShouldHandleException_WhenDatabaseErrorOccurs() {
        // Arrange
        when(brandEntityMapper.toBrandEntity(brand)).thenReturn(brandEntity);
        doThrow(new RuntimeException("Database error")).when(brandRepository).save(any(BrandEntity.class));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            brandMySqlAdapter.saveBrand(brand);
        });

        assertEquals("Database error", exception.getMessage());
    }

    @Test
    public void getPaginationBrands_ShouldReturnPagedResult() {
        // Arrange
        BrandPagingRequestDto requestDto = new BrandPagingRequestDto();
        requestDto.setPageNumber(1);
        requestDto.setPageSize(10);

        Pageable pageable = PageRequest.of(0, 10); // Página 0 porque es base 0
        List<BrandEntity> brandEntities = Collections.singletonList(brandEntity); // Simulamos la lista de entidades
        Page<BrandEntity> pagedResult = new PageImpl<>(brandEntities, pageable, 1); // Crea un Page<BrandEntity> simulado

        when(brandRepository.findAll(any(Pageable.class))).thenReturn(pagedResult); // Simula la llamada a findAll con cualquier Pageable
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand); // Mapea la entidad a la categoría

        // Act
        PagedList<Brand> result = brandMySqlAdapter.getPaginationBrands(requestDto);

        // Assert
        assertNotNull(result); // Asegúrate de que el resultado no es null
        assertEquals(1, result.getTotalElements()); // Verifica el número total de elementos
        assertEquals(1, result.getTotalPages()); // Verifica el número total de páginas
        assertEquals(Collections.singletonList(brand), result.getContent()); // Verifica el contenido
    }
}
