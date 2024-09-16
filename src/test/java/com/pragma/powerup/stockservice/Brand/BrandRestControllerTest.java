package com.pragma.powerup.stockservice.Brand;

import com.pragma.powerup.stockservice.adapters.driving.http.controller.BrandRestController;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IBrandHandler;
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
@WebMvcTest(controllers = BrandRestController.class)
public class BrandRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createBrand_InvalidRequest_ShouldReturnBadRequest() throws Exception {
        // Arrange: JSON con datos inválidos
        String brandRequestJson = "{\"name\":\"123@\",\"description\":\"46%5\"}";

        // Act & Assert: Validamos que el resultado sea un Bad Request (400)
        mockMvc.perform(post("/api/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrand_ValidRequest_ShouldReturnCreated() throws Exception {
        // Arrange: JSON válido
        String brandRequestJson = "{\"name\":\"Electronics\",\"description\":\"Devices\"}";

        // Simulamos el comportamiento del handler
        doNothing().when(brandHandler).createBrand(any(BrandCreateRequestDto.class));

        // Act & Assert: Validamos que el resultado sea un Created (201) con un mensaje
        mockMvc.perform(post("/api/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Marca creada con éxito"));
    }

    @Test
    void getBrandsPaged_ShouldReturnPagedBrands() throws Exception {
        // Arrange
        BrandResponseDto brandResponseDto = new BrandResponseDto(1L, "Electronics", "Devices");
        PagedList<BrandResponseDto> pagedBrands = new PagedList<>(List.of(brandResponseDto), 1, 10, 1);
        when(brandHandler.getPagedBrands(any(BrandPagingRequestDto.class))).thenReturn(pagedBrands);

        // Act & Assert
        mockMvc.perform(get("/api/brands?pageNumber=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Electronics"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}