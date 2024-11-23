package org.example.cybersecurityqabackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryDto {
    private Long id;

    private String name;

    private String description;

    private Long parentId;

    private String parentName;

    private List<SubCategoriesDto> subcategories;

    private List<QuestionDto> questions;
}