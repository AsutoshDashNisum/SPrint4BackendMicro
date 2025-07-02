package com.category_service.controller;

import com.category_service.dto.CategoryRequestDto;
import com.category_service.dto.CategoryResponseDto;
import com.category_service.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllActive() {
        return ResponseEntity.ok(categoryService.getAllActiveCategories());
    }
}
