package org.example.cybersecurityqabackend.service.category;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.dto.SubCategoriesDto;
import org.example.cybersecurityqabackend.entity.Category;
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
        // Tạo Category entity từ DTO
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

    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);

        // Ánh xạ subcategories dưới dạng SubCategoriesDto
        if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
            List<SubCategoriesDto> subcategoryDtos = new ArrayList<>();
            for (Category subcategory : category.getSubcategories()) {
                SubCategoriesDto subcategoryDto = new SubCategoriesDto();
                subcategoryDto.setId(subcategory.getId());
                subcategoryDto.setName(subcategory.getName());
                subcategoryDto.setDescription(subcategory.getDescription());
                subcategoryDtos.add(subcategoryDto);
            }
            dto.setSubcategories(subcategoryDtos);
        }

        return dto;
    }

    @Override
    public List<CategoryDto> getCategoriesWithSubcategories() {
        List<Category> categories = categoryRepository.findByParentIsNull();
        return mapToDTO(categories);
    }

    private List<CategoryDto> mapToDTO(List<Category> categories) {
        List<CategoryDto> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());

            // Ánh xạ các subcategories sang SubCategoriesDto
            if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
                List<SubCategoriesDto> subcategoryDTOs = mapToSimpleDTO(category.getSubcategories());
                dto.setSubcategories(subcategoryDTOs);
            }

            categoryDTOs.add(dto);
        }
        return categoryDTOs;
    }

    private List<SubCategoriesDto> mapToSimpleDTO(List<Category> subcategories) {
        List<SubCategoriesDto> subcategoryDTOs = new ArrayList<>();
        for (Category subcategory : subcategories) {
            SubCategoriesDto simpleDto = new SubCategoriesDto();
            simpleDto.setId(subcategory.getId());
            simpleDto.setName(subcategory.getName());
            simpleDto.setDescription(subcategory.getDescription());
            subcategoryDTOs.add(simpleDto);
        }
        return subcategoryDTOs;
    }

}
