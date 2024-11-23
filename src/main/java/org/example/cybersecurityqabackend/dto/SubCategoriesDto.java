package org.example.cybersecurityqabackend.dto;

import lombok.Data;

@Data
public class SubCategoriesDto {
    private Long id;
    private String name;
    private String description;
    private String parentName;
}
