package org.example.cybersecurityqabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.entity.Question;
import org.example.cybersecurityqabackend.repository.QuestionRepository;
import org.example.cybersecurityqabackend.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final QuestionRepository questionRepository;

    @GetMapping("/get")
    public List<CategoryDto> getCategoriesWithSubcategories() {
        return categoryService.getCategoriesWithSubcategories();
    }

    @GetMapping("/get/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/get/{categoryId}/questions")
    public List<Question> getQuestionsByCategory(@PathVariable Long categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }

    @PostMapping("/save")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
