package com.pragma.powerup.stockservice.Category;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.domains.exceptions.*;
import com.pragma.powerup.stockservice.domains.model.Category;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import com.pragma.powerup.stockservice.domains.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCategoryData")
    void testCreateCategoryWithInvalidInput(String name, String description, Class<? extends Exception> expectedException) {
        // Arrange
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        // Act & Assert
        assertThrows(expectedException, () -> categoryUseCase.createCategory(category));
    }

    static Stream<Arguments> provideInvalidCategoryData() {
        return Stream.of(
                Arguments.of(null, "Valid Description", CategoryFieldIsRequiredException.class),  // Null name
                Arguments.of("", "Valid Description", CategoryFieldIsRequiredException.class),    // Empty name
                Arguments.of("a".repeat(51), "Valid Description", CategoryFieldIsTooLongException.class),  // Name too long
                Arguments.of("Valid Name", null, CategoryFieldIsRequiredException.class),  // Null description
                Arguments.of("Valid Name", "", CategoryFieldIsRequiredException.class),   // Empty description
                Arguments.of("Valid Name", "a".repeat(91), CategoryFieldIsTooLongException.class)  // Description too long
        );
    }

    @Test
    void testCreateCategorySuccess() {
        // Arrange
        Category category = new Category("Valid Name", "Valid Description");
        when(categoryPersistencePort.getCategoryByName(category.getName())).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> categoryUseCase.createCategory(category));
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void testCreateCategoryWithExistingName() {
        // Arrange
        Category existingCategory = new Category("Existing Name", "Existing Description");
        when(categoryPersistencePort.getCategoryByName(existingCategory.getName())).thenReturn(existingCategory);

        // Act & Assert
        assertThrows(CategoryNameAlreadyExistsException.class, () -> categoryUseCase.createCategory(existingCategory));
    }

    @Test
    void testGetPaginationCategoryByOrder_ShouldReturnPagedCategories() {
        // Arrange
        CategoryPagingRequestDto requestDto = new CategoryPagingRequestDto();
        PagedList<Category> pagedCategories = new PagedList<>(List.of(new Category("Electronics", "Devices")), 1, 10, 1);
        when(categoryPersistencePort.getPaginationCategories(requestDto)).thenReturn(pagedCategories);

        // Act
        PagedList<Category> result = categoryUseCase.getPaginationCategoryByOrder(requestDto);

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }
}