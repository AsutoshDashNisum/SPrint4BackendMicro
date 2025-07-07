package com.category_service.service.impl;

import com.category_service.dto.CategoryRequestDto;
import com.category_service.dto.CategoryResponseDto;
import com.category_service.mapper.CategoryMapper;
import com.category_service.model.Category;
import com.category_service.repository.CategoryRepository;
import com.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto dto) {
        Category category = mapper.toEntity(dto);
        return mapper.toDto(categoryRepo.save(category));
    }

    @Override
    public List<CategoryResponseDto> getAllActiveCategories() {
        return categoryRepo.findByActiveTrue()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        return mapper.toDto(categoryRepo.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setActive(false);
        categoryRepo.save(category);
    }
}
