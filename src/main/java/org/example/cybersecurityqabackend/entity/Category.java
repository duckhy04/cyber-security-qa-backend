package org.example.cybersecurityqabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cybersecurityqabackend.dto.CategoryDto;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "categories")
public class Category extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @Column(length = 30)
    private String description;

    @Column(name = "parent_id", nullable = true)
    private Long parentId;

    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setParentId(parentId);
        return dto;
    }
}
