package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.enumclass.CateType;
import com.hello.day2.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .type(CateType.ETC)
                .title("")
                .regDate(LocalDateTime.now())
                .updateBy("admin")
                .build();

        Category createCategory = categoryRepository.save(category);
    }

    @Test
    public void update() {
        Optional<Category> findCategory = categoryRepository.findCategoryById(1L);

        findCategory.ifPresent(
            selectCategory -> {
                selectCategory.setTitle("노트북");
                selectCategory.setUpdateDate(LocalDateTime.now());
                categoryRepository.save(selectCategory);
            }
        );
    }

    @Test
    public void delete() {
        Optional<Category> deleteCategory = categoryRepository.findCategoryById(1L);

        deleteCategory.ifPresent(
            selectCategory -> categoryRepository.delete(selectCategory)
        );

        if (deleteCategory.isPresent()) {
            System.out.println("삭제 실패!");
        } else {
            System.out.println("삭제 성공!");
        }
    }
}
