package org.example.cybersecurityqabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDto> getCategoriesWithSubcategories() {
        return categoryService.getCategoriesWithSubcategories();
    }

    @PostMapping("/category/save")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }
}
