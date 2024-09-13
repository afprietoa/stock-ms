package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    @Query(value = "SELECT * FROM category WHERE name = :brandName", nativeQuery = true)
    Optional<BrandEntity> findByName(@Param("brandName") String brandName);
}
