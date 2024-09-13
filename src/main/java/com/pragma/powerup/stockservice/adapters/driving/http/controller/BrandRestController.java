package com.pragma.powerup.stockservice.adapters.driving.http.controller;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandCreateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.request.BrandUpdateRequestDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.*;
import com.pragma.powerup.stockservice.adapters.driving.http.handlers.IBrandHandler;
import com.pragma.powerup.stockservice.domains.model.PagedList;
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
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @Operation(summary = "Create Brand",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Brand created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Brand create fail.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("brand")
    public ResponseEntity<Map<String,String>> createBrand(@Valid @RequestBody BrandCreateRequestDto brandCreateRequestDto){
        brandHandler.createBrand(brandCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, BRAND_CREATED_MESSAGE));
    }

    @Operation(summary = "Get Brand Pagination",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Brand pagination was successful",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Brand pagination failed.",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/brands")
    public ResponseEntity<PagedList<BrandResponseDto>> getBrandsPaged(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) boolean isAscending
    ) {
        BrandPagingRequestDto requestDto = new BrandPagingRequestDto();
        requestDto.setPageNumber(pageNumber);
        requestDto.setPageSize(pageSize);
        requestDto.setOrderBy(orderBy);
        requestDto.setAscending(isAscending);

        PagedList<BrandResponseDto> brands = brandHandler.getBrandsPaged(requestDto);
        return ResponseEntity.ok(brands);
    }

//    @Operation(summary = "Updated a Brand",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Brand updated successfully.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Brand update fail.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @PatchMapping("brand")
//    public ResponseEntity<Map<String,String>> updateBrand(@Valid @RequestBody BrandUpdateRequestDto brandUpdateRequestDto){
//        brandHandler.updateBrand(brandUpdateRequestDto);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, BRAND_UPDATED_MESSAGE));
//    }
//
//    @Operation(summary = "Get Product Pagination",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Brand pagination was successful",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Brand pagination failed.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("brand/allByPagination")
//    public Page<BrandPaginationResponseDto> getPaginationBrand(@RequestParam Integer pageSize, @RequestParam String sortBy){
//        return brandHandler.getPaginationBrand(pageSize, sortBy);
//    }
//
//    @Operation(summary = "Get Product List",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Brand pagination was successful.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Brand pagination failed.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("brand/allByList")
//    public List<BrandListResponseDto> getListBrand(){
//        return brandHandler.getListBrand();
//    }
//
//    @Operation(summary = "Get Brand",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Brand returned.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "404", description = "Brand not found.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @GetMapping("brand/{idBrand}")
//    public ResponseEntity<BrandResponseDto> getBrand(@PathVariable Long idBrand) {
//        return ResponseEntity.ok(brandHandler.getBrand(idBrand));
//    }
//
//
//    @Operation(summary = "Delete Brand",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Brand deleted successfully",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
//                    @ApiResponse(responseCode = "409", description = "Brand deleted fail",
//                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
//    @DeleteMapping("brand/{idBrand}")
//    public ResponseEntity<Map<String, String>> deleteBrand(@PathVariable Long idBrand){
//        brandHandler.deleteBrand(idBrand);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, BRAND_DELETED_MESSAGE));
//    }

}
