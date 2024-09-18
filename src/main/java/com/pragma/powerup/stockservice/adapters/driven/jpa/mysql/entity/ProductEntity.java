package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product")
    private Long idProduct;
    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
    @Column(name="price")
    private BigDecimal price;
    @Size(max = 100, message = "Description must be less than 100 characters")
    @Column(name="description", nullable = false, length = 100)
    private String description;
    @Column(name="urlImage")
    private String urlImage;
    @Column(name="quantity")
    private BigDecimal quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category" )
    )
    // @JsonIgnoreProperties("products")
    private Set<CategoryEntity> categories = new HashSet<>();

    @Column(name="user_id")
    private  Long userId;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
