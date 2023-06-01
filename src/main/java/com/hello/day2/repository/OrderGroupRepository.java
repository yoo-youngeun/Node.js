package com.hello.day2.repository;

import com.hello.day2.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    Optional<OrderGroup> findOrderGroupById(Long id);
}
