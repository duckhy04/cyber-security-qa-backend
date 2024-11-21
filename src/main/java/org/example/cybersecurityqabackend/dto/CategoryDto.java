package org.example.cybersecurityqabackend.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
}
