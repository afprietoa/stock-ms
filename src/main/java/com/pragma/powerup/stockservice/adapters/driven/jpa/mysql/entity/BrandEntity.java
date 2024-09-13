package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_brand")
    private Long idBrand;
    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name="name", unique = true, nullable = false, length = 50)
    private String name;
    @Size(max = 120, message = "Description must be less than 120 characters")
    @Column(name="description", nullable = false, length = 120)
    private String description;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
