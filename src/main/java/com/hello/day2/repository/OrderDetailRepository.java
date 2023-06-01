package com.hello.day2.repository;

import com.hello.day2.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findOrderDetailById(Long id);
}
