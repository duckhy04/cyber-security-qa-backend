package org.example.cybersecurityqabackend.service.category;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.dto.SubCategoriesDto;
import org.example.cybersecurityqabackend.entity.Category;
import org.example.cybersecurityqabackend.exception.CustomInternalServerErrorException;
import org.example.cybersecurityqabackend.exception.CustomResourceNotFoundException;
import org.example.cybersecurityqabackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        if (categoryDto.getParentId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new CustomResourceNotFoundException("Parent category not found"));
            category.setParent(parentCategory);
        }

        Category savedCategory = categoryRepository.save(category);
        return toDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Category not found"));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        if (categoryDto.getParentId() != null) {
            if (categoryDto.getParentId().equals(category.getId())) {
                throw new CustomInternalServerErrorException("A category cannot be its own parent");
            }
            Category parentCategory = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new CustomResourceNotFoundException("Parent category not found"));
            category.setParent(parentCategory);
        } else {
            category.setParent(null);
        }

        Category updatedCategory = categoryRepository.save(category);
        return toDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getCategoriesWithSubcategories() {
        List<Category> categories = categoryRepository.findByParentIsNull();
        return mapToDTO(categories);
    }

    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);

        if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
            dto.setSubcategories(mapCategoriesToSubDtos(category.getSubcategories()));
        }

        return dto;
    }

    private List<SubCategoriesDto> mapCategoriesToSubDtos(List<Category> categories) {
        List<SubCategoriesDto> subcategoryDtos = new ArrayList<>();
        for (Category category : categories) {
            SubCategoriesDto dto = new SubCategoriesDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
            subcategoryDtos.add(dto);
        }
        return subcategoryDtos;
    }

    private List<CategoryDto> mapToDTO(List<Category> categories) {
        List<CategoryDto> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(toDto(category));
        }
        return categoryDTOs;
    }
}
