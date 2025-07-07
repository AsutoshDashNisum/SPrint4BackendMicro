package com.promo_service.service.impl;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;
import com.promo_service.mapper.PromoMapper;
import com.promo_service.model.Promo;
import com.promo_service.repository.PromoRepository;
import com.promo_service.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .filter(p -> "Active".equalsIgnoreCase(p.getStatus()))
                .orElseThrow(() -> new RuntimeException("Promo not found or inactive: " + promoCode));
        return promoMapper.toDto(promo);
    }

    @Override
    public PromoResponseDto updatePromo(String promoCode, PromoRequestDto dto) {
        Promo existing = promoRepo.findById(promoCode)
                .filter(p -> "Active".equalsIgnoreCase(p.getStatus()))
                .orElseThrow(() -> new RuntimeException("Promo not found or inactive: " + promoCode));

        existing.setPromo_type(dto.getPromo_type());
        existing.setDescription(dto.getDescription());
        existing.setAmount(dto.getAmount());

        Promo updated = promoRepo.save(existing);
        return promoMapper.toDto(updated);
    }

    @Override
    public void deletePromo(String promoCode) {
        Promo promo = promoRepo.findById(promoCode)
                .orElseThrow(() -> new RuntimeException("Promo not found: " + promoCode));
        promo.setStatus("Inactive");
        promoRepo.save(promo);
    }

    @Override
    public List<PromoResponseDto> getAllPromos() {
        return promoRepo.findByStatus("Active")
                .stream()
                .map(promoMapper::toDto)
                .collect(Collectors.toList());
    }
}