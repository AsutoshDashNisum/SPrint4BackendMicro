package com.product_service.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long productId;
    private String name;
    private String sku;
    private String status;
    private String categoryName;
}
