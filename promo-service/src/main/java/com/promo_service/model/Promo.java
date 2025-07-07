package com.promo_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promo {

    @Id
    @Column(length = 50)
    private String promoCode;

    @Column(nullable = false)
    private String promo_type;

    private String description;

    private Integer amount;

    @Column(nullable = false)
    private String status; // Active or Inactive
}