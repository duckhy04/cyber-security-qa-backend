package org.example.cybersecurityqabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cybersecurityqabackend.dto.CategoryDto;
import org.example.cybersecurityqabackend.dto.SubCategoriesDto;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "categories")
public class Category extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 30)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subcategories;

    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setParentId(this.parent != null ? this.parent.getId() : null);

        if (this.subcategories != null && !this.subcategories.isEmpty()) {
            dto.setSubcategories(this.subcategories.stream().map(sub -> {
                SubCategoriesDto subDto = new SubCategoriesDto();
                subDto.setId(sub.getId());
                subDto.setName(sub.getName());
                subDto.setDescription(sub.getDescription());
                subDto.setParentId(sub.getParent() != null ? sub.getParent().getId() : null);
                return subDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}
