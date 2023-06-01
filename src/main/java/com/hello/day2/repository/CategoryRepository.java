package com.hello.day2.repository;

import com.hello.day2.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryById(Long id);
}
