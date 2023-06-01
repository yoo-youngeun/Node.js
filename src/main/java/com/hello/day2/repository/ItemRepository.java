package com.hello.day2.repository;

import com.hello.day2.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findItemsById(Long id);
}
