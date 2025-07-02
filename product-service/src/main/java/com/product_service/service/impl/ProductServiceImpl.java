package com.product_service.service.impl;

import com.product_service.dto.ProductRequestDto;
import com.product_service.dto.ProductResponseDto;
import com.product_service.mapper.ProductMapper;
import com.product_service.model.Category;
import com.product_service.model.Product;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ProductRepository;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper mapper;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        Product product = mapper.toEntity(dto);
        product.setLastModified(LocalDate.now().toString());

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        return mapper.toDto(productRepo.save(product));
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getByCategoryId(Long categoryId) {
        return productRepo.findByCategory_CategoryId(categoryId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
