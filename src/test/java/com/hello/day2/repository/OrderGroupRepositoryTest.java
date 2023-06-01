package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.model.entity.OrderGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType("단일 구매")
                .status("입금 완료")
                .revAddress("서울시 관악구 신림동")
                .revName("집")
                .paymentType("카드결제")
                .totalPrice(130000L)
                .totalQuantity(1)
                .orderAt(LocalDateTime.now())
                .build();

        OrderGroup createOrderGroup = orderGroupRepository.save(orderGroup);
    }

    @Test
    public void update() {
        Optional<OrderGroup> findOrderGroup = orderGroupRepository.findOrderGroupById(1L);

        findOrderGroup.ifPresent(
                updateOrderGroup -> {
                    updateOrderGroup.setArrivalDate(LocalDateTime.now());
                    updateOrderGroup.setOrderType("묶음 구매");
                    updateOrderGroup.setRevAddress("서울시 강남구 대치동");
                    updateOrderGroup.setPaymentType("계좌이체");
                    updateOrderGroup.setTotalPrice(2548000L);
                    orderGroupRepository.save(updateOrderGroup);
                }
        );
    }

    @Test
    public void delete() {
        Optional<OrderGroup> findOrderGroup = orderGroupRepository.findOrderGroupById(1L);

        findOrderGroup.ifPresent(
                deleteOrderGroup -> orderGroupRepository.delete(deleteOrderGroup)
        );

        if (findOrderGroup.isPresent()) {
            System.out.println("삭제 실패!");
        } else {
            System.out.println("삭제 성공!");
        }
    }
}
