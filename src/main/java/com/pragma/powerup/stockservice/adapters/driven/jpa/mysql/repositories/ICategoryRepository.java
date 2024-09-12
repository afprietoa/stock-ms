package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "SELECT * FROM category WHERE name = :categoryName", nativeQuery = true)
    Optional<CategoryEntity> findByName(@Param("categoryName") String categoryName);


}
