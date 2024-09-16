package com.pragma.powerup.stockservice.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.controller.ProductRestController;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IProductHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Set;

import static com.pragma.powerup.stockservice.configuration.Constants.PRODUCT_CREATED_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Validated
@TestPropertySource(locations = "classpath:application-dev.yml")
@WebMvcTest(controllers = ProductRestController.class)
public class ProductRestControllerTest {
    @MockBean
    private IProductHandler productHandler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProduct_ValidRequest_Success() throws Exception {
        ProductCreateRequestDto requestDto = new ProductCreateRequestDto(
                "Product Name", BigDecimal.valueOf(100), "Description", "urlImage", BigDecimal.valueOf(1), Set.of(1L), 1L, 1L);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(PRODUCT_CREATED_MESSAGE));

        verify(productHandler, times(1)).createProduct(any(ProductCreateRequestDto.class));
    }

    @Test
    void createProduct_InvalidRequest_BadRequest() throws Exception {
        ProductCreateRequestDto invalidRequest = new ProductCreateRequestDto(
                "", BigDecimal.valueOf(-10), "Description", "urlImage", BigDecimal.valueOf(-1), Set.of(), null, null);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        verify(productHandler, times(0)).createProduct(any(ProductCreateRequestDto.class));
    }
}
