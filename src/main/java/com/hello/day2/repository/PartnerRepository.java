package com.hello.day2.repository;

import com.hello.day2.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findPartnerById(Long id);
}
