package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.BrandNotFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IBrandEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IBrandRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandListResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma.powerup.stockservice.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma.powerup.stockservice.domains.model.Brand;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandMySqlAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandMapper;


    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandMapper.toBrandEntity(brand));
    }

    @Override
    public Page<BrandPaginationResponseDto> getPaginationBrand(Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(0,pageSize, Sort.by(sortBy).ascending());
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);
        return brandEntityPage.map(brandMapper::toBrandPaginationResponseDto);
    }

    @Override
    public List<BrandListResponseDto> getListBrand() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        if (brandEntityList.isEmpty()) throw new NoDataFoundException();
        return brandEntityList.stream().map(brandMapper::toBrandListResponseDto).toList();
    }

    @Override
    public BrandResponseDto getBrandById(Long idBrand) {
        BrandEntity brandEntity = brandRepository.findById(idBrand).orElseThrow(BrandNotFoundException::new);
        return brandMapper.toBrandResponseDto(brandEntity);
    }

    @Override
    public void deleteBrand(Long idBrand) {
        brandRepository.deleteById(idBrand);
    }
}
