package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        Partner partner = Partner.builder()
                .name("삼성")
                .status("계약중")
                .address("서울시 강남구 양재동")
                .callCenter("010-1234-5678")
                .businessNumber("3333-01-4597677")
                .ceoName("김사과")
                .regDate(LocalDateTime.now())
                .categoryId(1L)
                .build();

        Partner createPartner = partnerRepository.save(partner);
    }

    @Test
    public void update() {
        Optional<Partner> findPartner = partnerRepository.findPartnerById(1L);

        findPartner.ifPresent(
                selectPartner -> {
                    selectPartner.setAddress("경기도 수원시 영통구");
                    selectPartner.setUpdateDate(LocalDateTime.now());
                    partnerRepository.save(selectPartner);
                }
        );
    }

    @Test
    public void delete() {
        Optional<Partner> findPartner = partnerRepository.findPartnerById(1L);

        findPartner.ifPresent(
                deletePartner -> partnerRepository.delete(deletePartner)
        );

        if (findPartner.isPresent()) {
            System.out.println("삭제 실패!");
        } else {
            System.out.println("삭제 성공!");

        }
    }
}
