package com.category_service.mapper;

import com.category_service.dto.CategoryRequestDto;
import com.category_service.dto.CategoryResponseDto;
import com.category_service.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDto dto);
    CategoryResponseDto toDto(Category category);
}
