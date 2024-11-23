package org.example.cybersecurityqabackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.dto.QuestionDto;
import org.example.cybersecurityqabackend.dto.UserDto;

import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "questions")
public class Question extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    public QuestionDto toDto() {
        QuestionDto dto = new QuestionDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setContent(this.content);

        // Chuyển đổi User sang UserDto
        Optional.ofNullable(this.user).ifPresent(user -> {
            UserDto userDto = user.toDto();
            dto.setUser(userDto);
        });

        // Chuyển đổi Category sang CategoryDto
        Optional.ofNullable(this.category).ifPresent(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setParentName(category.getParent().getName());
            dto.setCategory(categoryDto);
        });

        if (this.getCreatedAt() != null) {
            dto.setCreatedAt(this.getCreatedAt().toString());
        }
        if (this.getUpdatedAt() != null) {
            dto.setUpdatedAt(this.getUpdatedAt().toString());
        }

        return dto;
    }
}
