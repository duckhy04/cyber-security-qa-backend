package org.example.cybersecurityqabackend.repository;

import org.example.cybersecurityqabackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByParentId(Long parentId);

    boolean existsByName(String name);
}
