package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.model.entity.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = OrderDetail.builder()
                .arrivalDate(LocalDateTime.now())
                .status("출고 완료")
                .itemId(1L)
                .totalPrice(3800000L)
                .quantity(1)
                .regDate(LocalDateTime.now())
                .orderGroupId(1L)
                .build();

        OrderDetail createOrderDetail = orderDetailRepository.save(orderDetail);
    }

    @Test
    public void update() {
        Optional<OrderDetail> findOrderDetail = orderDetailRepository.findOrderDetailById(1L);

        findOrderDetail.ifPresent(
                selectOrderDetail -> {
                    selectOrderDetail.setOrderGroupId(2L);
                    selectOrderDetail.setQuantity(2);
                    orderDetailRepository.save(selectOrderDetail);
                }
        );
    }

    @Test
    public void delete() {
        Optional<OrderDetail> findOrderDetail = orderDetailRepository.findOrderDetailById(1L);

        findOrderDetail.ifPresent(
                deleteOrderDetail -> orderDetailRepository.delete(deleteOrderDetail)
        );

        if (findOrderDetail.isPresent()) {
            System.out.println("삭제 실패!");
        } else {
            System.out.println("삭제 성공!");
        }
    }
}
