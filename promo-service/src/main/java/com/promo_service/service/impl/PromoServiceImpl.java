package com.promo_service.service.impl;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;
import com.promo_service.mapper.PromoMapper;
import com.promo_service.model.Promo;
import com.promo_service.repository.PromoRepository;
import com.promo_service.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepo;
    private final PromoMapper promoMapper;

    @Override
    public PromoResponseDto createPromo(PromoRequestDto dto) {
        Promo promo = promoMapper.toEntity(dto);
        Promo saved = promoRepo.save(promo);
        return promoMapper.toDto(saved);
    }

    @Override
    public PromoResponseDto getPromoByCode(String promoCode) {
        Promo promo = promoRepo.findById(promoCode)
                .orElseThrow(() -> new RuntimeException("Promo not found"));
        return promoMapper.toDto(promo);
    }
}
