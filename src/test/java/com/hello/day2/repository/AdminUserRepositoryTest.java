package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = AdminUser.builder()
                .userid("admin")
                .userpw("admin")
                .name("김사과")
                .status("사용중")
                .regDate(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        // 객체가 정상적으로 저장되면 저장된 객체가 리턴됨.
    }

    @Test
    public void update() {
        Optional<AdminUser> findUser = adminUserRepository.findAdminUserByUserid("admin");

        // ifPresent : 값을 가지고 있는지 확인 후 예외처리
        findUser.ifPresent(
                selectUser -> {
                    selectUser.setName("관리자");
                    adminUserRepository.save(selectUser);
                }
        );
    }

    @Test
    public void delete() {
        Optional<AdminUser> findUser = adminUserRepository.findAdminUserByUserid("apple");
        // ifPresent : 객체가 값을 가지고 있는지 확인 후 예외처리

        findUser.ifPresent(
                selectUser -> adminUserRepository.delete(selectUser)
        );

        Optional<AdminUser> deleteUser = adminUserRepository.findAdminUserByUserid("apple");
        // isPresent : 객체 값 여부 리턴. null이 아니면 true, null이면 false
        if(deleteUser.isPresent()) {
            System.out.println("삭제 실패!");
        } else {
            System.out.println("삭제 성공!");

        }
    }
}
