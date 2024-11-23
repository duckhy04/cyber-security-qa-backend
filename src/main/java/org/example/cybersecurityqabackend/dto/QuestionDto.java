package org.example.cybersecurityqabackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String content;
    private UserDto user;
    private CategoryDto category;
    private String createdAt;
    private String updatedAt;
}
