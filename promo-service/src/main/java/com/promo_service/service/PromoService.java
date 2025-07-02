package com.promo_service.service;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;

public interface PromoService {
    PromoResponseDto createPromo(PromoRequestDto dto);
    PromoResponseDto getPromoByCode(String promoCode);
}
