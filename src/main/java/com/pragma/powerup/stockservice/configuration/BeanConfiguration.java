package com.pragma.powerup.stockservice.configuration;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.BrandMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.CategoryMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.adapter.ProductMySqlAdapter;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IBrandEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IBrandRepository;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.powerup.stockservice.adapters.driving.http.mapper.IProductResponseMapper;
import com.pragma.powerup.stockservice.domains.api.IBrandServicePort;
import com.pragma.powerup.stockservice.domains.api.ICategoryServicePort;
import com.pragma.powerup.stockservice.domains.api.IProductServicePort;
import com.pragma.powerup.stockservice.domains.spi.IBrandPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.ICategoryPersistencePort;
import com.pragma.powerup.stockservice.domains.spi.IProductPersistencePort;
import com.pragma.powerup.stockservice.domains.usecase.BrandUseCase;
import com.pragma.powerup.stockservice.domains.usecase.CategoryUseCase;
import com.pragma.powerup.stockservice.domains.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IProductResponseMapper productResponseMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductMySqlAdapter(productRepository, productEntityMapper);
    }
    @Bean
    public IProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), productResponseMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryMySqlAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(
                categoryPersistencePort()
//                , categoryResponseMapper
        );
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandMySqlAdapter(brandRepository, brandEntityMapper);
    }
    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort(), brandResponseMapper);
    }
}
