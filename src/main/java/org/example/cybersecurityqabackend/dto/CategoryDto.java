package org.example.cybersecurityqabackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;

    private String name;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;

    private List<SubCategoriesDto> subcategories;
}