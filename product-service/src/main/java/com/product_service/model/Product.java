package com.product_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    @Column(unique = true)
    private String sku;

    private String status;

    private String lastModified;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
