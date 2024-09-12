package com.pragma.powerup.stockservice.Category;

import com.pragma.powerup.stockservice.adapters.driving.http.controller.CategoryRestController;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.ICategoryHandler;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.powerup.stockservice.domains.api.ICategoryServicePort;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Validated
@TestPropertySource(locations = "classpath:application-dev.yml")
@WebMvcTest(controllers = CategoryRestController.class)
public class CategoryRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createCategory_InvalidRequest_ShouldReturnBadRequest() throws Exception {
        // Arrange: JSON con datos inválidos
        String categoryRequestJson = "{\"name\":\"123@\",\"description\":\"46%5\"}";

        // Act & Assert: Validamos que el resultado sea un Bad Request (400)
        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategory_ValidRequest_ShouldReturnCreated() throws Exception {
        // Arrange: JSON válido
        String categoryRequestJson = "{\"name\":\"Electronics\",\"description\":\"Devices\"}";

        // Simulamos el comportamiento del handler
        doNothing().when(categoryHandler).createCategory(any(CategoryCreateRequestDto.class));

        // Act & Assert: Validamos que el resultado sea un Created (201) con un mensaje
        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Categoría creada con éxito"));
    }

    @Test
    void getCategoriesPaged_ShouldReturnPagedCategories() throws Exception {
        // Arrange
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto(1L, "Electronics", "Devices");
        PagedList<CategoryResponseDto> pagedCategories = new PagedList<>(List.of(categoryResponseDto), 1, 10, 1);
        when(categoryHandler.getCategoriesPaged(any(CategoryPagingRequestDto.class))).thenReturn(pagedCategories);

        // Act & Assert
        mockMvc.perform(get("/api/categories?pageNumber=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Electronics"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
