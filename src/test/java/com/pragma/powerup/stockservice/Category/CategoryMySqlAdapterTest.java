package com.pragma.powerup.stockservice.Category;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.CategoryMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.stockservice.domains.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class CategoryMySqlAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    private CategoryMySqlAdapter categoryMySqlAdapter;

    private Category category;
    private CategoryEntity categoryEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryMySqlAdapter = new CategoryMySqlAdapter(categoryRepository, categoryEntityMapper);

        // Setup reusable objects
        category = new Category();
        category.setName("Electronics");
        category.setDescription("Devices");

        categoryEntity = new CategoryEntity();
    }

    @Test
    public void saveCategory_ShouldSaveCategory() {
        // Arrange
        when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryMySqlAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    public void findByName_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        String categoryName = "Electronics";
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        // Act
        Category result = categoryMySqlAdapter.getCategoryByName(categoryName);

        // Assert
        assertEquals(category, result);
    }

    @Test
    public void findByName_WhenCategoryDoesNotExist_ShouldReturnNull() {
        // Arrange
        String categoryName = "NonExistingCategory";
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());

        // Act
        Category result = categoryMySqlAdapter.getCategoryByName(categoryName);

        // Assert
        assertNull(result);
    }

    @Test
    public void saveCategory_ShouldHandleException_WhenDatabaseErrorOccurs() {
        // Arrange
        when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(categoryEntity);
        doThrow(new RuntimeException("Database error")).when(categoryRepository).save(any(CategoryEntity.class));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryMySqlAdapter.saveCategory(category);
        });

        assertEquals("Database error", exception.getMessage());
    }
}
