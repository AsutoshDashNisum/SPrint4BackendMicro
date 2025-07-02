package com.promo_service.dto;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromoRequestDto {
    private String promoCode;
    private String promo_type;
    private String description;
    private Integer amount;
}

