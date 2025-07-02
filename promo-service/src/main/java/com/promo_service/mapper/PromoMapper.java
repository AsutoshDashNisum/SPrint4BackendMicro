package com.promo_service.mapper;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;
import com.promo_service.model.Promo;
import org.springframework.stereotype.Component;

@Component
public class PromoMapper {

    public Promo toEntity(PromoRequestDto dto) {
        Promo promo = new Promo();
        promo.setPromoCode(dto.getPromoCode());
        promo.setPromo_type(dto.getPromo_type());
        promo.setDescription(dto.getDescription());
        promo.setAmount(dto.getAmount());
        return promo;
    }

    public PromoResponseDto toDto(Promo promo) {
        PromoResponseDto dto = new PromoResponseDto();
        dto.setPromoCode(promo.getPromoCode());
        dto.setPromo_type(promo.getPromo_type());
        dto.setDescription(promo.getDescription());
        dto.setAmount(promo.getAmount());
        return dto;
    }
}
