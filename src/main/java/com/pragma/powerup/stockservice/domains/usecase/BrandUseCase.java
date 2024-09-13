package com.pragma.powerup.stockservice.domains.usecase;

import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPagingRequestDto;
import com.pragma.powerup.stockservice.domains.api.IBrandServicePort;
import com.pragma.powerup.stockservice.domains.exceptions.BrandFieldIsRequiredException;
import com.pragma.powerup.stockservice.domains.exceptions.BrandFieldIsTooLongException;
import com.pragma.powerup.stockservice.domains.exceptions.BrandNameAlreadyExistsException;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.model.PagedList;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;
//    private final IBrandResponseMapper brandResponseMapper;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort
//                        ,IBrandResponseMapper brandResponseMapper
    ) {
        this.brandPersistencePort = brandPersistencePort;
//        this.brandResponseMapper = brandResponseMapper;
    }

    @Override
    public void createBrand(Brand brand) {
        validateBrand(brand);
        brandPersistencePort.saveBrand(brand);
    }

    private void validateBrand(Brand brand) {
        validateField(brand.getName(), "Nombre de la marca", 50);
        validateField(brand.getDescription(), "Descripción de la marca", 90);

        if (brandPersistencePort.getBrandByName(brand.getName()) != null) {
            throw new BrandNameAlreadyExistsException("El nombre de la marca ya existe.");
        }
    }

    private void validateField(String field, String fieldName, int maxLength) {
        if (field == null || field.isEmpty()) {
            throw new BrandFieldIsRequiredException(fieldName + " es requerido.");
        }
        if (field.length() > maxLength) {
            throw new BrandFieldIsTooLongException(fieldName + " excede el límite de " + maxLength + " caracteres.");
        }
    }

    @Override
    public PagedList<Brand> getPaginationBrandByOrder(BrandPagingRequestDto requestDto) {
        return brandPersistencePort.getPaginationBrands(requestDto);
    }


//    @Override
//    public void updateBrand(Brand brand) {
//        Brand tempBrand = brandResponseMapper.toBrand(this.getBrand(brand.getIdBrand()));
//        tempBrand.setName(brand.getName());
//        tempBrand.setDescription(brand.getDescription());
//        brandPersistencePort.saveBrand(tempBrand);
//    }
//
//    @Override
//    public Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy) {
//        return brandPersistencePort.getPaginationBrand(pageSize, sortBy);
//    }
//
//    @Override
//    public List<BrandListResponseDto> getListBrand() {
//        return brandPersistencePort.getListBrand();
//    }
//
//    @Override
//    public BrandResponseDto getBrand(Long idBrand) {
//        return brandPersistencePort.getBrandById(idBrand);
//    }
//
//    @Override
//    public void deleteBrand(Long idBrand) {
//        brandPersistencePort.deleteBrand(idBrand);
//    }
}
