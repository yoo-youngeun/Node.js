package com.hello.day2.repository;

import com.hello.day2.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // @Repository 를 사용하면 sql문을 생성하지 않고 테이블 접근이 가능.
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findAdminUserByUserid(String userid);
}
