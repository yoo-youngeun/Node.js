package com.hello.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AdminUser { // DB 테이블 명 admin_user -> AdminUser(카멜표기법)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String userpw;
    private String name;
    private String status;
    private LocalDateTime lastLoginAt;
    private LocalDateTime regDate;
    private String createBy;
}
