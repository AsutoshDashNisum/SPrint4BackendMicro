package com.category_service.dto;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long categoryId;
    private String categoryName;
    private String description;
}
