package com.product_service.mapper;

import com.product_service.dto.ProductRequestDto;
import com.product_service.dto.ProductResponseDto;
import com.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setStatus(dto.getStatus());
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setStatus(product.getStatus());
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null);
        return dto;
    }
}
