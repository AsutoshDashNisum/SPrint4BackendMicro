package com.category_service.mapper;

import com.category_service.dto.CategoryRequestDto;
import com.category_service.dto.CategoryResponseDto;
import com.category_service.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        category.setActive(true); // default value
        return category;
    }

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
