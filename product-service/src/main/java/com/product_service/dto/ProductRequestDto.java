package com.product_service.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String sku;
    private Long categoryId;
    private String status;
}
