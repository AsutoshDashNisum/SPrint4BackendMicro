package com.promo_service.service;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;

import java.util.List;

public interface PromoService {
    PromoResponseDto createPromo(PromoRequestDto dto);
    PromoResponseDto getPromoByCode(String promoCode);
    PromoResponseDto updatePromo(String promoCode, PromoRequestDto dto);
    void deletePromo(String promoCode);
    List<PromoResponseDto> getAllPromos();
}