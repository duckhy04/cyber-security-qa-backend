package org.example.cybersecurityqabackend.service.category;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.entity.Category;
import org.example.cybersecurityqabackend.exception.CustomResourceNotFoundException;
import org.example.cybersecurityqabackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {

        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new CustomResourceNotFoundException("Category with name '" + categoryDto.getName() + "' already exists.");
        }

        if (categoryDto.getParentId() != null && categoryDto.getParentId() == 0) {
            throw new CustomResourceNotFoundException("Invalid parentId: 0. Parent category cannot be 0.");
        }

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setParentId(categoryDto.getParentId());

        // Lưu vào database
        return categoryRepository.save(category).toDto();
    }




    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream().map(Category::toDto).collect(Collectors.toList());
    }
}
