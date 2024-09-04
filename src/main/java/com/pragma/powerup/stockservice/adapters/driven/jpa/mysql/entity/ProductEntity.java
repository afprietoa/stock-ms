package com.pragma.powerup.stockservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Column(name="name")
    private String name;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="description")
    private String description;
    @Column(name="urlImage")
    private String urlImage;
    @Column(name="quantity")
    private BigDecimal quantity;
    @ManyToOne
    //@JoinColumn(name = "brand_id")
    private BrandEntity brandId;
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category" )
    )
    // @JsonIgnoreProperties("products")
    private Set<CategoryEntity> categories;
    @Column(name="user_id")
    private  Long userId;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
