package org.example.cybersecurityqabackend.service.category;

import org.example.cybersecurityqabackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    List<CategoryDto> getCategoriesWithSubcategories();

    CategoryDto getCategoryById(Long categoryId);
}
