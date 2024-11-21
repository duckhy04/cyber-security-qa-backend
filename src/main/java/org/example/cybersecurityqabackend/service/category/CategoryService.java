package org.example.cybersecurityqabackend.service.category;

import org.example.cybersecurityqabackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategories();
}
