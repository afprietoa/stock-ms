package com.pragma.powerup.stockservice.adapters.driving.http.controller;


import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.ProductUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.ProductResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.pragma.powerup.stockservice.configuration.Constants.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductRestController {
    private final IProductHandler productHandler;

    @Operation(summary = "Create Product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Product create fail.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("product")
    public ResponseEntity<Map<String,String>> createProduct(@Valid @RequestBody ProductCreateRequestDto productCreateRequestDto){
        productHandler.createProduct(productCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PRODUCT_CREATED_MESSAGE));
    }

//    @Operation(summary = "Updated a Product",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Product updated successfully.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Product update fail.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @PatchMapping("product")
//    public ResponseEntity<Map<String,String>> updateProduct(@Valid @RequestBody ProductUpdateRequestDto productUpdateRequestDto){
//        productHandler.updateProduct(productUpdateRequestDto);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PRODUCT_UPDATED_MESSAGE));
//    }
//
//    @Operation(summary = "Get Product Pagination",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Product pagination was successful",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Product pagination failed.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("product/allByPagination")
//    public Page<ProductPaginationResponseDto> getPaginationProduct(@RequestParam Integer pageSize, @RequestParam String sortBy){
//        return productHandler.getPaginationProduct(pageSize, sortBy);
//    }
//
//    @Operation(summary = "Get Product List",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Product pagination was successful.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Product pagination failed.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("product/allByList")
//    public List<ProductListResponseDto> getListProduct(){
//        return productHandler.getListProduct();
//    }
//
//    @Operation(summary = "Get Product",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Product returned.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "404", description = "Product not found.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("product/{idProduct}")
//    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long idProduct) {
//        return ResponseEntity.ok(productHandler.getProduct(idProduct));
//    }
//
//
//    @Operation(summary = "Delete Product",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Product deleted successfully",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Product deleted fail",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @DeleteMapping("product/{idProduct}")
//    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long idProduct){
//        productHandler.deleteProduct(idProduct);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PRODUCT_DELETED_MESSAGE));
//    }

}
