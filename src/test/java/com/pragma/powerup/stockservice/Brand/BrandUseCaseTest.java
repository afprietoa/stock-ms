package com.pragma.powerup.stockservice.Brand;

import com.pragma.powerup.stockservice.domains.exceptions.*;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.usecase.BrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private BrandUseCase brandUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brandUseCase = new BrandUseCase(brandPersistencePort);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidBrandData")
    void testCreateBrandWithInvalidInput(String name, String description, Class<? extends Exception> expectedException) {
        // Arrange
        Brand brand = new Brand();
        brand.setName(name);
        brand.setDescription(description);

        // Act & Assert
        assertThrows(expectedException, () -> brandUseCase.createBrand(brand));
    }

    static Stream<Arguments> provideInvalidBrandData() {
        return Stream.of(
                Arguments.of(null, "Valid Description", BrandFieldIsRequiredException.class),  // Null name
                Arguments.of("", "Valid Description", BrandFieldIsRequiredException.class),    // Empty name
                Arguments.of("a".repeat(51), "Valid Description", BrandFieldIsTooLongException.class),  // Name too long
                Arguments.of("Valid Name", null, BrandFieldIsRequiredException.class),  // Null description
                Arguments.of("Valid Name", "", BrandFieldIsRequiredException.class),   // Empty description
                Arguments.of("Valid Name", "a".repeat(91), BrandFieldIsTooLongException.class)  // Description too long
        );
    }

    @Test
    void testCreateBrandSuccess() {
        // Arrange
        Brand brand = new Brand("Valid Name", "Valid Description");
        when(brandPersistencePort.getBrandByName(brand.getName())).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> brandUseCase.createBrand(brand));
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void testCreateBrandWithExistingName() {
        // Arrange
        Brand existingBrand = new Brand("Existing Name", "Existing Description");
        when(brandPersistencePort.getBrandByName(existingBrand.getName())).thenReturn(existingBrand);

        // Act & Assert
        assertThrows(BrandNameAlreadyExistsException.class, () -> brandUseCase.createBrand(existingBrand));
    }
}
