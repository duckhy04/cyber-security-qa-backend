package org.example.cybersecurityqabackend.service.category;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.entity.Category;
import org.example.cybersecurityqabackend.exception.CustomInternalServerErrorException;
import org.example.cybersecurityqabackend.exception.CustomResourceNotFoundException;
import org.example.cybersecurityqabackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return savedCategory.toDto();
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
        return updatedCategory.toDto();
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
        return categories.stream().map(Category::toDto).toList();
    }
}

