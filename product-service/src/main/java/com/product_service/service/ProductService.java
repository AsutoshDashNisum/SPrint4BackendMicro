package com.product_service.service;

import com.product_service.dto.ProductRequestDto;
import com.product_service.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto dto);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getByCategoryId(Long categoryId);
    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);
    void deleteProduct(Long id);
}
