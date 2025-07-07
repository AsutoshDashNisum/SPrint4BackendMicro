package com.promo_service.dto;

import lombok.Data;

@Data
public class PromoRequestDto {
    private String promoCode;
    private String promo_type;
    private String description;
    private Integer amount;
}
