package com.category_service.service;

import com.category_service.dto.CategoryRequestDto;
import com.category_service.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto addCategory(CategoryRequestDto dto);
    List<CategoryResponseDto> getAllActiveCategories();
}
