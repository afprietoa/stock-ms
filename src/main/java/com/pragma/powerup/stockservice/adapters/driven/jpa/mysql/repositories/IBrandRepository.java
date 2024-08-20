package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
}
