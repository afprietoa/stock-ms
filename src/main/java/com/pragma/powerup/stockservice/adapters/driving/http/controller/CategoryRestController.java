package com.pragma.powerup.stockservice.adapters.driving.http.controller;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.ICategoryHandler;
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
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Create Category",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Category create fail.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("category")
    public ResponseEntity<Map<String,String>> createCategory(@Valid @RequestBody CategoryCreateRequestDto categoryCreateRequestDto){
        categoryHandler.createCategory(categoryCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CATEGORY_CREATED_MESSAGE));
    }

    @Operation(summary = "Updated a Category",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category updated successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Category update fail.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PatchMapping("category")
    public ResponseEntity<Map<String,String>> updateCategory(@Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        categoryHandler.updateCategory(categoryUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CATEGORY_UPDATED_MESSAGE));
    }

    @Operation(summary = "Get Category Pagination",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category pagination was successful",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Category pagination failed.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("category/allByPagination")
    public Page<CategoryPaginationResponseDto> getPaginationCategory(@RequestParam Integer pageSize, @RequestParam String sortBy){
        return categoryHandler.getPaginationCategory(pageSize, sortBy);
    }

    @Operation(summary = "Get Category List",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category pagination was successful.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Category pagination failed.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("category/allByList")
    public List<CategoryListResponseDto> getListCategory(){
        return categoryHandler.getListCategory();
    }

    @Operation(summary = "Get Category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category returned.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Category not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("category/{idCategory}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long idCategory) {
        return ResponseEntity.ok(categoryHandler.getCategory(idCategory));
    }


    @Operation(summary = "Delete Category",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category deleted successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Category deleted fail",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @DeleteMapping("category/{idCategory}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long idCategory){
        categoryHandler.deleteCategory(idCategory);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CATEGORY_DELETED_MESSAGE));
    }
}
