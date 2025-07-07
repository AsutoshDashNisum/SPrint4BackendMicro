package com.promo_service.controller;

import com.promo_service.dto.PromoRequestDto;
import com.promo_service.dto.PromoResponseDto;
import com.promo_service.service.PromoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promos")
@RequiredArgsConstructor
public class PromoController {

    private final PromoService promoService;

    @PostMapping
    public ResponseEntity<PromoResponseDto> createPromo(@Valid @RequestBody PromoRequestDto dto) {
        return ResponseEntity.ok(promoService.createPromo(dto));
    }

    @GetMapping
    public ResponseEntity<List<PromoResponseDto>> getAllPromos() {
        return ResponseEntity.ok(promoService.getAllPromos());
    }

    @GetMapping("/{promoCode}")
    public ResponseEntity<PromoResponseDto> getPromo(@PathVariable String promoCode) {
        return ResponseEntity.ok(promoService.getPromoByCode(promoCode));
    }

    @PutMapping("/{promoCode}")
    public ResponseEntity<PromoResponseDto> updatePromo(@PathVariable String promoCode, @RequestBody PromoRequestDto dto) {
        return ResponseEntity.ok(promoService.updatePromo(promoCode, dto));
    }

    @DeleteMapping("/{promoCode}")
    public ResponseEntity<Void> deletePromo(@PathVariable String promoCode) {
        promoService.deletePromo(promoCode);
        return ResponseEntity.noContent().build();
    }
}