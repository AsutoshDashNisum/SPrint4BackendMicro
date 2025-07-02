package com.promo_service.controller;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;
import com.promo_service.service.PromoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promos")
@RequiredArgsConstructor
public class PromoController {

    private final PromoService promoService;

    @PostMapping
    public ResponseEntity<PromoResponseDto> createPromo(@Valid @RequestBody PromoRequestDto dto) {
        try {
            return ResponseEntity.ok(promoService.createPromo(dto));
        } catch (Exception e) {
            e.printStackTrace(); // 👈 log full stack trace
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/{promoCode}")
    public ResponseEntity<PromoResponseDto> getPromo(@PathVariable String promoCode) {
        return ResponseEntity.ok(promoService.getPromoByCode(promoCode));
    }
}
