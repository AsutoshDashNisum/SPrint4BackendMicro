package com.product_service.service.impl;

import com.product_service.dto.ProductRequestDto;
import com.product_service.dto.ProductResponseDto;
import com.product_service.mapper.ProductMapper;
import com.product_service.model.Category;
import com.product_service.model.Product;
import com.product_service.model.ProductCategory;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ProductRepository;
import com.product_service.repository.ProductCategoryRepository;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final ProductCategoryRepository productCategoryRepo;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        // Ensure SKU exists in ProductAttributes table
        jdbcTemplate.update(
                "IF NOT EXISTS (SELECT 1 FROM ProductAttributes WHERE sku = ?) " +
                        "INSERT INTO ProductAttributes (sku) VALUES (?)",
                dto.getSku(), dto.getSku()
        );

        Product product = mapper.toEntity(dto);
        product.setLastModified(LocalDate.now().toString());

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(dto.getCategoryId());
        productCategory.setSku(dto.getSku());
        productCategory.setPrice(dto.getPrice());
        productCategory.setDiscount(dto.getDiscount());
        productCategoryRepo.save(productCategory);

        return mapper.toDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepo.findAll().stream()
                .filter(product -> "Active".equalsIgnoreCase(product.getStatus()))
                .map(product -> {
                    ProductResponseDto dto = mapper.toDto(product);
                    addPricingDetails(product, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getByCategoryId(Long categoryId) {
        return productRepo.findByCategory_CategoryId(categoryId).stream()
                .filter(product -> "Active".equalsIgnoreCase(product.getStatus()))
                .map(product -> {
                    ProductResponseDto dto = mapper.toDto(product);
                    addPricingDetails(product, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setStatus(dto.getStatus());
        product.setLastModified(LocalDate.now().toString());
        product.setCategory(category);

        Product updatedProduct = productRepo.save(product);

        ProductCategory pc = productCategoryRepo.findByCategoryIdAndSku(dto.getCategoryId(), dto.getSku());
        if (pc != null) {
            pc.setPrice(dto.getPrice());
            pc.setDiscount(dto.getDiscount());
        } else {
            pc = new ProductCategory();
            pc.setCategoryId(dto.getCategoryId());
            pc.setSku(dto.getSku());
            pc.setPrice(dto.getPrice());
            pc.setDiscount(dto.getDiscount());
        }
        productCategoryRepo.save(pc);

        return mapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus("Inactive");
        product.setLastModified(LocalDate.now().toString());
        productRepo.save(product);
    }

    private void addPricingDetails(Product product, ProductResponseDto dto) {
        if (product.getSku() == null || product.getCategory() == null) {
            dto.setPrice(0);
            dto.setDiscount(0.0);
            dto.setDiscountedPrice(0.0);
            return;
        }

        ProductCategory pc = productCategoryRepo.findByCategoryIdAndSku(
                product.getCategory().getCategoryId(), product.getSku()
        );

        if (pc != null) {
            dto.setPrice(pc.getPrice());
            dto.setDiscount(pc.getDiscount());
            double discountedPrice = pc.getPrice() - (pc.getPrice() * pc.getDiscount() / 100);
            dto.setDiscountedPrice(discountedPrice);
        } else {
            dto.setPrice(0);
            dto.setDiscount(0.0);
            dto.setDiscountedPrice(0.0);
        }
    }
}
